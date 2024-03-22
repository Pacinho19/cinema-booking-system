package pl.pacinho.cinemabookingsystem.screening.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.pacinho.cinemabookingsystem.screening.model.entity.Screening;

import java.util.List;
import java.util.Optional;

public interface ScreeningRepository {
    @Query(value = """
            from Screening s
            join s.movie m
            join fetch s.room r
            join fetch m.category c
            where m.id=:id
            """)
    List<Screening> findAllByMovieIdWithFetchRoom(@Param("id") int id);

    boolean existsById(int screeningId);

    @Query(value = """
            from Screening s
            join fetch s.room r
            where s.id=:id
            """)
    Optional<Screening> findByIdWithFetchRoom(@Param("id") int id);

    @Query(value = """
            from Screening s
            join fetch s.room r
            join fetch s.movie m
            join fetch m.category
            where s.id=:id
            """)
    Optional<Screening> findByIdWithFetchRoomAndMovie(@Param("id") int id);
}
