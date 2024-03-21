package pl.pacinho.cinemabookingsystem.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.cinemabookingsystem.ticket.model.entity.Ticket;

@Repository
interface TicketJpaRepository extends TicketRepository, JpaRepository<Ticket, Integer> {
}
