package pl.pacinho.cinemabookingsystem.ticket.payment.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.pacinho.cinemabookingsystem.ticket.model.entity.Ticket;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class TicketPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uuid;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @CreationTimestamp
    private LocalDateTime completedAt;

    public TicketPayment(Ticket ticket) {
        this.ticket = ticket;
        this.uuid = UUID.randomUUID().toString();
    }
}
