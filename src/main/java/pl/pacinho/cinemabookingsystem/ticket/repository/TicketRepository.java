package pl.pacinho.cinemabookingsystem.ticket.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.pacinho.cinemabookingsystem.ticket.model.entity.Ticket;
import pl.pacinho.cinemabookingsystem.ticket.model.enums.TicketState;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {

    Ticket save(Ticket ticket);

    List<Ticket> findAllByState(TicketState ticketState);

    void delete(Ticket ticket);

    @Query(value = """
            from Ticket t
            join fetch t.screeningSeat ss
            join fetch ss.screening s
            join fetch s.movie m
            join fetch s.room r
            join fetch m.category c
            where t.uuid=:uuid
            """)
    Optional<Ticket> findByUuidWithFetch(@Param("uuid") String uuid);

    Optional<Ticket> findByUuid(String uuid);
}
