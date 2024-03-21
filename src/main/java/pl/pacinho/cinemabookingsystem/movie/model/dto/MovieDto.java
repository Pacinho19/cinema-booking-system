package pl.pacinho.cinemabookingsystem.movie.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MovieDto {
    private final String name;
    private final String description;
    private final String category;
    private final LocalDate releaseDate;
    private final int duration;
}
