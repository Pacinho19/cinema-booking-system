package pl.pacinho.cinemabookingsystem.screening.model.mapper;

import pl.pacinho.cinemabookingsystem.movie.model.mapper.MovieMapper;
import pl.pacinho.cinemabookingsystem.room.model.mapper.RoomMapper;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningDto;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningWithRoomDto;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningWithRoomNumberDto;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningWithMoveAndSeatsDto;
import pl.pacinho.cinemabookingsystem.screening.model.entity.Screening;
import pl.pacinho.cinemabookingsystem.screening.seat.model.entity.ScreeningSeat;
import pl.pacinho.cinemabookingsystem.screening.tools.ScreeningSeatPlanGenerator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScreeningMapper {

    public static ScreeningDto convertToDto(Screening screening) {
        return ScreeningDto.builder()
                .id(screening.getId())
                .date(screening.getDate())
                .time(screening.getTime())
                .ticketPrice(screening.getTicketPrice())
                .build();
    }

    public static ScreeningWithRoomDto convertToDtoWithRoom(Screening screening) {
        return ScreeningWithRoomDto.builder()
                .date(screening.getDate())
                .time(screening.getTime())
                .room(RoomMapper.convertToDto(screening.getRoom()))
                .ticketPrice(screening.getTicketPrice())
                .build();
    }

    public static ScreeningWithRoomNumberDto convertToDtoWithRoomNumber(Screening screening) {
        return ScreeningWithRoomNumberDto.builder()
                .date(screening.getDate())
                .time(screening.getTime())
                .roomNumber(screening.getRoom().getNumber())
                .ticketPrice(screening.getTicketPrice())
                .build();
    }

    public static List<ScreeningDto> convertToDtoList(Set<Screening> screenings) {
        return screenings.stream()
                .map(ScreeningMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public static ScreeningWithMoveAndSeatsDto convertToDtoWithSeats(Screening screening, List<ScreeningSeat> screeningSeats){
        return ScreeningWithMoveAndSeatsDto.builder()
                .movie(MovieMapper.convertToDto(screening.getMovie()))
                .date(screening.getDate())
                .time(screening.getTime())
                .ticketPrice(screening.getTicketPrice())
                .roomNumber(screening.getRoom().getNumber())
                .seats(ScreeningSeatPlanGenerator.generate(screening, screeningSeats))
                .build();
    }
}
