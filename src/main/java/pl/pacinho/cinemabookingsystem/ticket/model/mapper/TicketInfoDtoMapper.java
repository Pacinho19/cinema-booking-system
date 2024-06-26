package pl.pacinho.cinemabookingsystem.ticket.model.mapper;

import pl.pacinho.cinemabookingsystem.movie.model.mapper.MovieMapper;
import pl.pacinho.cinemabookingsystem.screening.model.mapper.ScreeningMapper;
import pl.pacinho.cinemabookingsystem.screening.seat.model.mapper.ScreeningSeatMapper;
import pl.pacinho.cinemabookingsystem.ticket.model.dto.TicketInfoDto;
import pl.pacinho.cinemabookingsystem.ticket.model.entity.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketInfoDtoMapper {
    public static TicketInfoDto convert(Ticket ticket) {
        return TicketInfoDto.builder()
                .uuid(ticket.getUuid())
                .ticketState(ticket.getState())
                .reservationDate(ticket.getDate())
                .screeningSeat(ScreeningSeatMapper.convert(ticket.getScreeningSeat()))
                .screening(ScreeningMapper.convertToDtoWithRoomNumber(ticket.getScreeningSeat().getScreening()))
                .movie(MovieMapper.convertToDto(ticket.getScreeningSeat().getScreening().getMovie()))
                .build();
    }

    public static List<TicketInfoDto> convertToDtoList(List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketInfoDtoMapper::convert)
                .collect(Collectors.toList());
    }
}