package pl.pacinho.cinemabookingsystem.screening.seat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.cinemabookingsystem.screening.seat.model.entity.ScreeningSeat;

@Repository
interface ScreeningSeatJpaRepository extends ScreeningSeatRepository, JpaRepository<ScreeningSeat, Integer> {
}
