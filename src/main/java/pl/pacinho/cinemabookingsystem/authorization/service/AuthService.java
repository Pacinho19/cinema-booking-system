package pl.pacinho.cinemabookingsystem.authorization.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.cinemabookingsystem.config.security.jwt.JwtAuth;
import pl.pacinho.cinemabookingsystem.user.model.entity.CinemaUser;
import pl.pacinho.cinemabookingsystem.user.service.CinemaUserService;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final CinemaUserService cinemaUserService;
    private final JwtAuth jwtAuth;

    public String generateToken(String username) {
        CinemaUser expenseTrackerUser = cinemaUserService.getByName(username);
        return jwtAuth.generateToken(expenseTrackerUser.getLogin());
    }
}
