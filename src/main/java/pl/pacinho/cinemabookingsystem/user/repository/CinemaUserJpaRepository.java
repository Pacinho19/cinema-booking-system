package pl.pacinho.cinemabookingsystem.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.cinemabookingsystem.user.model.entity.CinemaUser;

import java.util.Optional;

@Repository
interface CinemaUserJpaRepository extends CinemaUserRepository, JpaRepository<CinemaUser, Integer> {
    Optional<CinemaUser> findByLogin(String username);
}
