package pl.pacinho.cinemabookingsystem.screening.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.cinemabookingsystem.screening.model.entity.Screening;

@Repository
public interface ScreeningJpaRepository extends ScreeningRepository, JpaRepository<Screening, Integer> {
}
