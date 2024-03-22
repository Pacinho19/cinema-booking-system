package pl.pacinho.cinemabookingsystem.movie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.pacinho.cinemabookingsystem.category.model.entity.Category;
import pl.pacinho.cinemabookingsystem.category.repository.CategoryRepository;
import pl.pacinho.cinemabookingsystem.movie.model.dto.MovieDto;
import pl.pacinho.cinemabookingsystem.movie.model.dto.MoviePage;
import pl.pacinho.cinemabookingsystem.movie.model.dto.MovieWithScreeningDto;
import pl.pacinho.cinemabookingsystem.movie.model.dto.NewMovieDto;
import pl.pacinho.cinemabookingsystem.movie.model.entity.Movie;
import pl.pacinho.cinemabookingsystem.movie.model.mapper.MovieMapper;
import pl.pacinho.cinemabookingsystem.movie.repository.MovieRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    public List<MovieDto> findAll() {
        return MovieMapper.convertToDtoList(
                movieRepository.findAll()
        );
    }

    public Movie save(NewMovieDto movieDto) {
        Category category = categoryRepository.findByName(movieDto.getCategory())
                .orElse(new Category(movieDto.getCategory()));

        Movie newMovie = MovieMapper.convertToEntity(movieDto, category);
        return movieRepository.save(newMovie);
    }

    public boolean existsById(int id) {
        return movieRepository.existsById(id);
    }

    public MoviePage findAllPageable(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber - 1, size, Sort.by("id"));
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        Page<Movie> movies = movieRepository.findAll(pageable);
        List<MovieDto> moviesDtos = MovieMapper.convertToDtoList(movies.getContent());
        return new MoviePage(moviesDtos, currentPage + 1, movies.getTotalElements(), movies.getTotalPages(), pageSize);
    }

    public MovieWithScreeningDto findByAlias(String alias) {
        Movie movie = movieRepository.findByAliasWithScreening(alias)
                .orElseThrow(() -> new EntityNotFoundException("Could not find movie with given alias: " + alias));

        return MovieMapper.convertToDtoWithScreenings(movie);
    }
}
