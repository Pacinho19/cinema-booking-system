package pl.pacinho.cinemabookingsystem.screening.model.dto;

import lombok.Getter;
import pl.pacinho.cinemabookingsystem.screening.seat.model.enums.SeatState;

@Getter
public class ScreeningSeatStatusDto extends ScreeningSeatDto {

    private final SeatState state;

    public ScreeningSeatStatusDto(int row, int seat, SeatState state) {
        super(row, seat);
        this.state = state;
    }
}
