package pl.pacinho.cinemabookingsystem.ticket.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.pacinho.cinemabookingsystem.screening.model.entity.Screening;
import pl.pacinho.cinemabookingsystem.screeningseat.model.entity.ScreeningSeat;
import pl.pacinho.cinemabookingsystem.ticket.model.enums.TicketState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uuid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "screening_seat_id")
    private ScreeningSeat screeningSeat;

    @Enumerated(EnumType.STRING)
    private TicketState state;

    @CreationTimestamp
    private LocalDateTime date;

    public Ticket(ScreeningSeat screeningSeat) {
        this.uuid = UUID.randomUUID().toString();
        this.screeningSeat = screeningSeat;
        this.state = TicketState.UNPAID;
    }
}
