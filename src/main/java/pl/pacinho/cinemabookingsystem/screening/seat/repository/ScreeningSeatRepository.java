package pl.pacinho.cinemabookingsystem.screening.seat.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.pacinho.cinemabookingsystem.screening.seat.model.entity.ScreeningSeat;
import pl.pacinho.cinemabookingsystem.screening.seat.model.enums.SeatState;

import java.util.List;

public interface ScreeningSeatRepository {
    @Query(value = """
            from ScreeningSeat sr
            join fetch sr.screening s
            join fetch s.room r
            where s.id=:screeningId
            and sr.state in :states
            """)
    List<ScreeningSeat> findAllByScreeningIdAndStateIn(@Param("screeningId") int screeningId, @Param("states") List<SeatState> states);

    ScreeningSeat save(ScreeningSeat screeningSeat);

    @Query(value = """
            select count(*)>0
            from ScreeningSeat ss
            join ss.screening s
            where s.id=:screeningId
            and ss.row=:row
            and ss.seat=:seat
            and ss.state=:state
            """)
    boolean existsByScreeningIdAndRowAndSeatAndSeatState(@Param("screeningId") int screeningId, @Param("row") int row, @Param("seat") int seat, @Param("state") SeatState state);
}
