package pl.pacinho.cinemabookingsystem.ticket.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.pacinho.cinemabookingsystem.screening.seat.model.entity.ScreeningSeat;
import pl.pacinho.cinemabookingsystem.screening.seat.model.enums.SeatState;
import pl.pacinho.cinemabookingsystem.ticket.model.dto.TicketInfoDto;
import pl.pacinho.cinemabookingsystem.ticket.model.entity.Ticket;
import pl.pacinho.cinemabookingsystem.ticket.model.enums.TicketState;
import pl.pacinho.cinemabookingsystem.ticket.model.mapper.TicketInfoDtoMapper;
import pl.pacinho.cinemabookingsystem.ticket.repository.TicketRepository;
import pl.pacinho.cinemabookingsystem.user.model.entity.CinemaUser;
import pl.pacinho.cinemabookingsystem.user.service.CinemaUserService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final int MAX_PAID_AGE;
    private final TicketRepository ticketRepository;
    private final CinemaUserService cinemaUserService;

    public TicketService(final @Value("${ticket.max-paid-age}") int MAX_PAID_AGE, TicketRepository ticketRepository, CinemaUserService cinemaUserService) {
        this.MAX_PAID_AGE = MAX_PAID_AGE;
        this.ticketRepository = ticketRepository;
        this.cinemaUserService = cinemaUserService;
    }

    public Ticket save(CinemaUser cinemaUser, ScreeningSeat screeningSeat) {
        return ticketRepository.save(
                new Ticket(cinemaUser, screeningSeat)
        );
    }

    public void cancelAllUnpaidTickets() {
        ticketRepository.findAllByStateWithFetchSeat(TicketState.UNPAID)
                .stream()
                .filter(ticket -> ChronoUnit.MINUTES.between(ticket.getDate(), LocalDateTime.now()) > MAX_PAID_AGE)
                .peek(ticket -> {
                    ticket.setState(TicketState.CANCELLED);
                    ticket.getScreeningSeat().setState(SeatState.CANCELLED);
                })
                .forEach(ticketRepository::save);
    }

    public TicketInfoDto getTicketInfo(String uuid) {
        return ticketRepository.findByUuidWithFetch(uuid)
                .map(TicketInfoDtoMapper::convert)
                .orElseThrow(() -> new EntityNotFoundException("Could not find screening with given identifier: " + uuid));
    }

    public List<TicketInfoDto> getUserTickets(Optional<Authentication> authentication) {
        CinemaUser cinemaUser = authentication.map(auth -> cinemaUserService.getByName(auth.getName()))
                .orElseThrow(() -> new IllegalArgumentException("Authentication error"));

        return TicketInfoDtoMapper.convertToDtoList(
                ticketRepository.findByUserWithFetch(cinemaUser)
        );

    }
}
