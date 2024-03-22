package pl.pacinho.cinemabookingsystem.screeningseat.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.pacinho.cinemabookingsystem.screening.model.entity.Screening;
import pl.pacinho.cinemabookingsystem.screeningseat.model.enums.SeatState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class ScreeningSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @Column(name = "row_number")
    private int row;

    @Column(name = "seat_number")
    private int seat;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ScreeningSeat(Screening screening, int row, int seat) {
        this.uuid = UUID.randomUUID().toString();
        this.screening = screening;
        this.row = row;
        this.seat = seat;
    }
}
