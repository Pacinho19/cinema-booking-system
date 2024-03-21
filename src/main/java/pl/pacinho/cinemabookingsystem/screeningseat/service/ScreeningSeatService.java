package pl.pacinho.cinemabookingsystem.screeningseat.service;

import org.springframework.stereotype.Service;
import pl.pacinho.cinemabookingsystem.exception.model.IllegalSeatPositionException;
import pl.pacinho.cinemabookingsystem.exception.model.SeatReservationException;
import pl.pacinho.cinemabookingsystem.room.model.entity.Room;
import pl.pacinho.cinemabookingsystem.screening.model.entity.Screening;
import pl.pacinho.cinemabookingsystem.screening.repository.ScreeningRepository;
import pl.pacinho.cinemabookingsystem.screeningseat.model.entity.ScreeningSeat;
import pl.pacinho.cinemabookingsystem.screeningseat.repository.ScreeningSeatRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ScreeningSeatService {

    private final ScreeningRepository screeningRepository;
    private final ScreeningSeatRepository screeningSeatRepository;
    private final Map<String, Lock> locks;

    public ScreeningSeatService(ScreeningRepository screeningRepository, ScreeningSeatRepository screeningSeatRepository) {
        this.screeningRepository = screeningRepository;
        this.screeningSeatRepository = screeningSeatRepository;
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

            boolean isSeatReserved = screeningSeatRepository.existsByScreeningIdAndRowAndSeat(screeningId, row, seat);
            if (isSeatReserved)
                throw new SeatReservationException();
        } finally {
            locks.computeIfAbsent(String.valueOf(screeningId), key -> new ReentrantLock())
                    .unlock();
        }

        return save(screening, row, seat).getUuid();

    }

    private ScreeningSeat save(Screening screening, int row, int seat) {
        return screeningSeatRepository.save(
                new ScreeningSeat(screening, row, seat)
        );
    }
}
