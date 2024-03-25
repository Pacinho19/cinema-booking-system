package pl.pacinho.cinemabookingsystem.movie.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@SuperBuilder
public class MovieDto {

    private final String title;
    private final String alias;
    private final String briefDescription;
    private final String description;
    private final String category;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate releaseDate;
    private final int duration;
    private final String imgUrl;
}
