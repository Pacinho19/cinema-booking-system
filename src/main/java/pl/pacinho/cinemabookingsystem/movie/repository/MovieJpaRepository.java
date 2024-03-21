package pl.pacinho.cinemabookingsystem.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.cinemabookingsystem.movie.model.entity.Movie;

@Repository
interface MovieJpaRepository extends MovieRepository, JpaRepository<Movie, Integer> {
}
