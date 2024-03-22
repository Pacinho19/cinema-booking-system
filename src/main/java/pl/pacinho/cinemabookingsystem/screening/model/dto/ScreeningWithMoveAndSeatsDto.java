package pl.pacinho.cinemabookingsystem.screening.model.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import pl.pacinho.cinemabookingsystem.movie.model.dto.MovieDto;

@Getter
@SuperBuilder
public class ScreeningWithMoveAndSeatsDto extends ScreeningWithRoomNumberDto {

    private MovieDto movie;
    private ScreeningSeatStatusDto[][] seats;
}
