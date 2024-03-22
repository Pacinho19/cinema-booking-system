package pl.pacinho.cinemabookingsystem.screening.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ScreeningSeatDto {

    private final int row;
    private final int seat;
}
