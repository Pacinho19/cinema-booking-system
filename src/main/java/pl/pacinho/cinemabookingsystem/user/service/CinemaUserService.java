package pl.pacinho.cinemabookingsystem.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pacinho.cinemabookingsystem.user.model.CinemaUserDetails;
import pl.pacinho.cinemabookingsystem.user.model.entity.CinemaUser;
import pl.pacinho.cinemabookingsystem.user.repository.CinemaUserRepository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class CinemaUserService implements UserDetailsService {

    private final CinemaUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CinemaUserDetails(getByName(username));
    }

    public CinemaUser getByName(String username) {
        return userRepository.findByLogin(username)
                       .orElseThrow(() -> new EntityNotFoundException("Cannot find user by username : " + username));
    }
}
