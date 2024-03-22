package pl.pacinho.cinemabookingsystem.screening.seat.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.pacinho.cinemabookingsystem.screening.seat.model.entity.ScreeningSeat;

import java.util.List;

public interface ScreeningSeatRepository {
    @Query(value = """
            from ScreeningSeat sr
            join fetch sr.screening s
            join fetch s.room r
            where s.id=:screeningId
            """)
    List<ScreeningSeat> findAllByScreeningId(@Param("screeningId") int screeningId);

    ScreeningSeat save(ScreeningSeat screeningSeat);

    boolean existsByScreeningIdAndRowAndSeat(int screeningId, int row, int seat);
}
