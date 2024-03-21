package pl.pacinho.cinemabookingsystem.screeningseat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.cinemabookingsystem.screeningseat.model.entity.ScreeningSeat;

@Repository
interface ScreeningSeatJpaRepository extends ScreeningSeatRepository, JpaRepository<ScreeningSeat, Integer> {
}
