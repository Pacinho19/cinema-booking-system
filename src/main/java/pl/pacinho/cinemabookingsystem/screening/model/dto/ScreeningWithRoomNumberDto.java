package pl.pacinho.cinemabookingsystem.screening.model.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import pl.pacinho.cinemabookingsystem.room.model.dto.RoomDto;

@Getter
@SuperBuilder
public class ScreeningWithRoomNumberDto extends ScreeningDto {

    private int roomNumber;
}
