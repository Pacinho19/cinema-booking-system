package pl.pacinho.cinemabookingsystem.movie.model.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningDto;

import java.util.List;

@Getter
@SuperBuilder
public class MovieWithScreeningDto extends MovieDto {

    private List<ScreeningDto> screenings;

}
