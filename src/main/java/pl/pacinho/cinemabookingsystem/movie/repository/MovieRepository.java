package pl.pacinho.cinemabookingsystem.movie.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.pacinho.cinemabookingsystem.movie.model.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    @Query(value = """
            from Movie m
            join fetch m.category
            """)
    List<Movie> findAll();

    Movie save(Movie newMovie);

    boolean existsById(Integer id);

    Page<Movie> findAll(Pageable pageable);

    @Query(value = """
            select distinct m
            from Movie m
            join fetch m.category
            left join fetch m.screenings
            where m.alias=:alias
            """)
    Optional<Movie> findByAliasWithScreening(@Param("alias") String alias);
}
