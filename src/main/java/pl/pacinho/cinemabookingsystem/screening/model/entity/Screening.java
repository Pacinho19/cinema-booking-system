package pl.pacinho.cinemabookingsystem.screening.model.entity;

import lombok.Getter;
import pl.pacinho.cinemabookingsystem.movie.model.entity.Movie;
import pl.pacinho.cinemabookingsystem.room.model.entity.Room;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private BigDecimal ticketPrice;
}
