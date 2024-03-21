package pl.pacinho.cinemabookingsystem.room.model.mapper;

import pl.pacinho.cinemabookingsystem.room.model.dto.RoomDto;
import pl.pacinho.cinemabookingsystem.room.model.entity.Room;

public class RoomMapper {
    public static RoomDto convertToDto(Room room) {
        return new RoomDto(
                room.getNumber(),
                room.getRowsCount(),
                room.getSeatsCount()
        );
    }
}
