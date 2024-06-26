package pl.pacinho.cinemabookingsystem.screening.seat.model.mapper;

import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningSeatDto;
import pl.pacinho.cinemabookingsystem.screening.seat.model.entity.ScreeningSeat;

public class ScreeningSeatMapper {
    public static ScreeningSeatDto convert(ScreeningSeat screeningSeat) {
        return new ScreeningSeatDto(
                screeningSeat.getRow(),
                screeningSeat.getSeat()
        );
    }
}
