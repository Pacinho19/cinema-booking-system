package pl.pacinho.cinemabookingsystem.screening.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.pacinho.cinemabookingsystem.screening.service.ScreeningService;
import pl.pacinho.cinemabookingsystem.screening.seat.service.ScreeningSeatService;

import java.util.Optional;

@RequestMapping("/screening")
@RequiredArgsConstructor
@RestController
public class ScreeningController {

    private final ScreeningService screeningService;
    private final ScreeningSeatService screeningSeatService;

    @GetMapping("/{screeningId}")
    ResponseEntity<?> getScreeningWithSeats(@PathVariable("screeningId") int screeningId) {
        return ResponseEntity.ok(
                screeningService.findScreeningWithSeats(screeningId)
        );
    }

    @GetMapping("/{screeningId}/seat")
    ResponseEntity<?> getScreeningSeatsStatus(@PathVariable("screeningId") int screeningId) {
        return ResponseEntity.ok(
                screeningService.findScreeningSeatsStatus(screeningId)
        );
    }

    @PostMapping("/{screeningId}/reservation")
    ResponseEntity<?> reservationSeat(Authentication authentication,
                                      @PathVariable("screeningId") int screeningId,
                                      @RequestParam(name = "row") int row,
                                      @RequestParam(name = "seat") int seat) {
        return ResponseEntity.ok(
                screeningSeatService.reservation(Optional.ofNullable(authentication), screeningId, row, seat)
        );
    }

}
