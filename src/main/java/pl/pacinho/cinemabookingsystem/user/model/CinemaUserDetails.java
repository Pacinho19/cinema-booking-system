package pl.pacinho.cinemabookingsystem.user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.pacinho.cinemabookingsystem.user.model.entity.CinemaUser;

import java.util.Collection;
import java.util.List;

public class CinemaUserDetails implements UserDetails {

    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public CinemaUserDetails(CinemaUser expenseTrackerUser) {
        this.authorities = List.of(new SimpleGrantedAuthority(expenseTrackerUser.getRole()));
        this.userName = expenseTrackerUser.getLogin();
        this.password = expenseTrackerUser.getPassword();
        this.active = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
