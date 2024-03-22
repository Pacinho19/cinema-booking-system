package pl.pacinho.cinemabookingsystem.movie.model.mapper;

import pl.pacinho.cinemabookingsystem.category.model.entity.Category;
import pl.pacinho.cinemabookingsystem.movie.model.dto.MovieDto;
import pl.pacinho.cinemabookingsystem.movie.model.dto.MovieWithScreeningDto;
import pl.pacinho.cinemabookingsystem.movie.model.dto.NewMovieDto;
import pl.pacinho.cinemabookingsystem.movie.model.entity.Movie;
import pl.pacinho.cinemabookingsystem.screening.model.mapper.ScreeningMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {
    public static List<MovieDto> convertToDtoList(List<Movie> movies) {
        return movies.stream()
                .map(MovieMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public static MovieDto convertToDto(Movie movie) {
        return MovieDto.builder()
                .title(movie.getTitle())
                .alias(movie.getAlias())
                .description(movie.getDescription())
                .category(movie.getCategory().getName())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .imgUrl(movie.getImgUrl())
                .build();
    }

    public static Movie convertToEntity(NewMovieDto movieDto, Category category) {
        return Movie.builder()
                .title(movieDto.getTitle())
                .alias(movieDto.getAlias())
                .category(category)
                .description(movieDto.getDescription())
                .duration(movieDto.getDuration())
                .releaseDate(movieDto.getReleaseDate())
                .imgUrl(movieDto.getImgUrl())
                .build();
    }

    public static MovieWithScreeningDto convertToDtoWithScreenings(Movie movie) {
        return MovieWithScreeningDto.builder()
                .title(movie.getTitle())
                .alias(movie.getAlias())
                .description(movie.getDescription())
                .category(movie.getCategory().getName())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .imgUrl(movie.getImgUrl())
                .screenings(ScreeningMapper.convertToDtoList(movie.getScreenings()))
                .build();
    }

}
