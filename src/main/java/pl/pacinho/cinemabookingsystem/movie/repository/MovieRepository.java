package pl.pacinho.cinemabookingsystem.movie.repository;

import org.springframework.data.jpa.repository.Query;
import pl.pacinho.cinemabookingsystem.movie.model.entity.Movie;

import java.util.List;

public interface MovieRepository {

    @Query(value = """
            from Movie m
            join fetch m.category
            """)
    List<Movie> findAll();
    Movie save(Movie newMovie);
    boolean existsById(Integer id);
}
