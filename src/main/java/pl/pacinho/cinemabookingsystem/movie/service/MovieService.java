package pl.pacinho.cinemabookingsystem.movie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.cinemabookingsystem.category.model.entity.Category;
import pl.pacinho.cinemabookingsystem.category.repository.CategoryRepository;
import pl.pacinho.cinemabookingsystem.movie.model.dto.MovieDto;
import pl.pacinho.cinemabookingsystem.movie.model.dto.NewMovieDto;
import pl.pacinho.cinemabookingsystem.movie.model.entity.Movie;
import pl.pacinho.cinemabookingsystem.movie.model.mapper.MovieMapper;
import pl.pacinho.cinemabookingsystem.movie.repository.MovieRepository;

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
}
