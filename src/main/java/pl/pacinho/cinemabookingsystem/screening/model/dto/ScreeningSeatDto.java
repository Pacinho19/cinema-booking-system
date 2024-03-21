package pl.pacinho.cinemabookingsystem.screening.model.dto;

import pl.pacinho.cinemabookingsystem.screeningseat.model.enums.SeatState;

public record ScreeningSeatDto(int row, int seat, SeatState state) {
}
