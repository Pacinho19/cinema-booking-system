package pl.pacinho.cinemabookingsystem.ticket.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.pacinho.cinemabookingsystem.screening.seat.model.entity.ScreeningSeat;
import pl.pacinho.cinemabookingsystem.ticket.model.enums.TicketState;
import pl.pacinho.cinemabookingsystem.user.model.entity.CinemaUser;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Setter
    @Enumerated(EnumType.STRING)
    private TicketState state;

    private LocalDateTime date;

    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private CinemaUser user;

    public Ticket(CinemaUser cinemaUser, ScreeningSeat screeningSeat) {
        this.uuid = UUID.randomUUID().toString();
        this.screeningSeat = screeningSeat;
        this.state = TicketState.UNPAID;
        this.price = screeningSeat.getScreening().getTicketPrice();
        this.user = cinemaUser;
    }

    @PrePersist
    void prePersist() {
        date = LocalDateTime.now();
    }
}
