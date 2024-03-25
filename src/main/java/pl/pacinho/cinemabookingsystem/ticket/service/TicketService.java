package pl.pacinho.cinemabookingsystem.ticket.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.pacinho.cinemabookingsystem.screening.seat.model.entity.ScreeningSeat;
import pl.pacinho.cinemabookingsystem.screening.seat.model.enums.SeatState;
import pl.pacinho.cinemabookingsystem.ticket.model.dto.TicketInfoDto;
import pl.pacinho.cinemabookingsystem.ticket.model.entity.Ticket;
import pl.pacinho.cinemabookingsystem.ticket.model.enums.TicketState;
import pl.pacinho.cinemabookingsystem.ticket.model.mapper.TicketInfoDtoMapper;
import pl.pacinho.cinemabookingsystem.ticket.repository.TicketRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class TicketService {

    private final int MAX_PAID_AGE;
    private final TicketRepository ticketRepository;

    public TicketService(final @Value("${ticket.max-paid-age}") int MAX_PAID_AGE, TicketRepository ticketRepository) {
        this.MAX_PAID_AGE = MAX_PAID_AGE;
        this.ticketRepository = ticketRepository;
    }

    public Ticket save(ScreeningSeat screeningSeat) {
        return ticketRepository.save(
                new Ticket(screeningSeat)
        );
    }

    public void cancelAllUnpaidTickets() {
        ticketRepository.findAllByStateWithFetchSeat(TicketState.UNPAID)
                .stream()
                .filter(ticket -> ChronoUnit.MINUTES.between(ticket.getDate(), LocalDateTime.now()) > MAX_PAID_AGE)
                .peek(ticket -> {
                    ticket.setState(TicketState.CANCELLED);
                    ticket.getScreeningSeat().setState(SeatState.CANCELLED);
                })
                .forEach(ticketRepository::save);
    }

    public TicketInfoDto getTicketInfo(String uuid) {
        return ticketRepository.findByUuidWithFetch(uuid)
                .map(TicketInfoDtoMapper::convert)
                .orElseThrow(() -> new EntityNotFoundException("Could not find screening with given identifier: " + uuid));
    }
}
