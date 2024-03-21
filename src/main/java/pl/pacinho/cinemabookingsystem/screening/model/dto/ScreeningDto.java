package pl.pacinho.cinemabookingsystem.screening.model.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@SuperBuilder(toBuilder = true)
public class ScreeningDto {

    private LocalDate date;
    private LocalTime time;
}
