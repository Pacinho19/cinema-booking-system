package pl.pacinho.cinemabookingsystem.movie.model.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NewMovieDto extends MovieDto {

    private final String imgUrl;

    NewMovieDto(String name, String description, String category, LocalDate releaseDate, int duration, String imgUrl) {
        super(name, description, category, releaseDate, duration);
        this.imgUrl = imgUrl;
    }
}
