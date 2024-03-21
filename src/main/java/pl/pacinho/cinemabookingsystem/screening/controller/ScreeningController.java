package pl.pacinho.cinemabookingsystem.screening.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pacinho.cinemabookingsystem.screening.service.ScreeningService;
import pl.pacinho.cinemabookingsystem.screeningseat.service.ScreeningSeatService;

@RequestMapping("/screening")
@RequiredArgsConstructor
@RestController
public class ScreeningController {

    private final ScreeningService screeningService;
    private final ScreeningSeatService screeningSeatService;

    @GetMapping("/{screeningId}/seat")
    ResponseEntity<?> getScreeningSeats(@PathVariable("screeningId") int screeningId) {
        return ResponseEntity.ok(
                screeningService.findScreeningSeats(screeningId)
        );
    }

    @PostMapping("/{screeningId}/reservation")
    ResponseEntity<?> reservationSeat(@PathVariable("screeningId") int screeningId,
                                      @RequestParam(name = "row") int row,
                                      @RequestParam(name = "seat") int seat) {
        return ResponseEntity.ok(
                screeningSeatService.reservation(screeningId, row, seat)
        );
    }

}