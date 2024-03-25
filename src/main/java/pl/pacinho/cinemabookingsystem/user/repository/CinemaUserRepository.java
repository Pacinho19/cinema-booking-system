package pl.pacinho.cinemabookingsystem.user.repository;


import pl.pacinho.cinemabookingsystem.user.model.entity.CinemaUser;

import java.util.Optional;

public interface CinemaUserRepository {
    Optional<CinemaUser> findByLogin(String username);
}
