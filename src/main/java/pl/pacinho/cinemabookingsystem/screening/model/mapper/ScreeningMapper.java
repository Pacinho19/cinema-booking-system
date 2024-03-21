package pl.pacinho.cinemabookingsystem.screening.model.mapper;

import pl.pacinho.cinemabookingsystem.room.model.mapper.RoomMapper;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningWithRoomDto;
import pl.pacinho.cinemabookingsystem.screening.model.entity.Screening;

public class ScreeningMapper {

    public static ScreeningWithRoomDto convertToDtoWithRoom(Screening screening) {
        return ScreeningWithRoomDto.builder()
                .date(screening.getDate())
                .time(screening.getTime())
                .room(RoomMapper.convertToDto(screening.getRoom()))
                .build();
    }
}
