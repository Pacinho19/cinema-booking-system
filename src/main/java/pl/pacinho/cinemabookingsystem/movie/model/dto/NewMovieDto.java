package pl.pacinho.cinemabookingsystem.movie.model.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NewMovieDto extends MovieDto {

    NewMovieDto(String name, String alias, String briefDescription, String description, String category, LocalDate releaseDate, int duration, String imgUrl) {
        super(name, alias, briefDescription, description, category, releaseDate, duration, imgUrl);
    }
}
