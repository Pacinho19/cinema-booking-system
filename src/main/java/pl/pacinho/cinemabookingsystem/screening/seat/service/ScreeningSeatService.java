package pl.pacinho.cinemabookingsystem.screening.seat.service;

import org.springframework.stereotype.Service;
import pl.pacinho.cinemabookingsystem.exception.model.IllegalSeatPositionException;
import pl.pacinho.cinemabookingsystem.exception.model.SeatReservationException;
import pl.pacinho.cinemabookingsystem.room.model.entity.Room;
import pl.pacinho.cinemabookingsystem.screening.model.entity.Screening;
import pl.pacinho.cinemabookingsystem.screening.repository.ScreeningRepository;
import pl.pacinho.cinemabookingsystem.screening.seat.model.enums.SeatState;
import pl.pacinho.cinemabookingsystem.screening.seat.repository.ScreeningSeatRepository;
import pl.pacinho.cinemabookingsystem.screening.seat.model.entity.ScreeningSeat;
import pl.pacinho.cinemabookingsystem.ticket.service.TicketService;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ScreeningSeatService {

    private final ScreeningRepository screeningRepository;
    private final ScreeningSeatRepository screeningSeatRepository;
    private final TicketService ticketService;
    private final Map<String, Lock> locks;

    public ScreeningSeatService(ScreeningRepository screeningRepository, ScreeningSeatRepository screeningSeatRepository, TicketService ticketService) {
        this.screeningRepository = screeningRepository;
        this.screeningSeatRepository = screeningSeatRepository;
        this.ticketService = ticketService;
        this.locks = new ConcurrentHashMap<>();
    }

    public String reservation(int screeningId, int row, int seat) {
        if (row < 1 || seat < 1)
            throw new IllegalSeatPositionException();

        Screening screening = screeningRepository.findByIdWithFetchRoom(screeningId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find screening with given id: " + screeningId));

        Room room = screening.getRoom();
        if (row > room.getRowsCount() || seat > room.getSeatsCount())
            throw new IllegalSeatPositionException();

        try {
            locks.computeIfAbsent(String.valueOf(screeningId), key -> new ReentrantLock())
                    .lock();

            boolean isSeatReserved = screeningSeatRepository.existsByScreeningIdAndRowAndSeatAndSeatState(screeningId, row, seat, SeatState.UNAVAILABLE);
            if (isSeatReserved)
                throw new SeatReservationException();

            ScreeningSeat screeningSeat = new ScreeningSeat(screening, row, seat);
            return ticketService.save(screeningSeat).getUuid();

        } finally {
            locks.computeIfAbsent(String.valueOf(screeningId), key -> new ReentrantLock())
                    .unlock();
        }

    }
}
