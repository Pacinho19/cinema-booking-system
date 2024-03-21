package pl.pacinho.cinemabookingsystem.ticket.repository;

import pl.pacinho.cinemabookingsystem.ticket.model.entity.Ticket;
import pl.pacinho.cinemabookingsystem.ticket.model.enums.TicketState;

import java.util.List;

public interface TicketRepository {

    Ticket save(Ticket ticket);

    List<Ticket> findAllByState(TicketState ticketState);

    void delete(Ticket ticket);
}
