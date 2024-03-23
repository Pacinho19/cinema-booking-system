package pl.pacinho.cinemabookingsystem.ticket.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import pl.pacinho.cinemabookingsystem.movie.model.dto.MovieDto;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningDto;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningSeatDto;
import pl.pacinho.cinemabookingsystem.ticket.model.enums.TicketState;

import java.time.LocalDateTime;

@Builder
@Getter
public class TicketInfoDto {

    private String uuid;
    private TicketState ticketState;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationDate;
    private ScreeningSeatDto screeningSeat;
    private ScreeningDto screening;
    private MovieDto movie;
}