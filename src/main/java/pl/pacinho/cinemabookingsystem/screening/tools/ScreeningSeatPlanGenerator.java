package pl.pacinho.cinemabookingsystem.screening.tools;

import pl.pacinho.cinemabookingsystem.room.model.entity.Room;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningSeatStatusDto;
import pl.pacinho.cinemabookingsystem.screening.model.entity.Screening;
import pl.pacinho.cinemabookingsystem.screeningseat.model.entity.ScreeningSeat;
import pl.pacinho.cinemabookingsystem.screeningseat.model.enums.SeatState;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScreeningSeatPlanGenerator {
    public static ScreeningSeatStatusDto[][] generate(Screening screening, List<ScreeningSeat> screeningSeats) {
        Room room = screening.getRoom();

        Map<String, SeatState> seatStates = getSeatStatesMap(screeningSeats);

        return IntStream.rangeClosed(1, room.getRowsCount())
                .boxed()
                .map(row -> IntStream.rangeClosed(1, room.getSeatsCount())
                        .boxed()
                        .map(seat -> new ScreeningSeatStatusDto(row, seat, seatStates.getOrDefault(row + "," + seat, SeatState.AVAILABLE)))
                        .toArray(ScreeningSeatStatusDto[]::new))
                .toArray(ScreeningSeatStatusDto[][]::new);


    }

    private static  Map<String, SeatState> getSeatStatesMap(List<ScreeningSeat> screeningSeats) {
        return screeningSeats
                .stream()
                .collect(Collectors.toMap(s -> s.getRow() + "," + s.getSeat(), s -> SeatState.UNAVAILABLE));
    }
}
