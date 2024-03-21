package pl.pacinho.cinemabookingsystem.ticket.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.pacinho.cinemabookingsystem.screeningseat.model.entity.ScreeningSeat;
import pl.pacinho.cinemabookingsystem.ticket.model.entity.Ticket;
import pl.pacinho.cinemabookingsystem.ticket.model.enums.TicketState;
import pl.pacinho.cinemabookingsystem.ticket.repository.TicketRepository;

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

    public void deleteAllUnpaidTickets() {
        ticketRepository.findAllByState(TicketState.UNPAID)
                .stream()
                .filter(ticket -> ChronoUnit.MINUTES.between(ticket.getDate(), LocalDateTime.now()) > MAX_PAID_AGE)
                .forEach(ticketRepository::delete);
    }
}