package pl.pacinho.cinemabookingsystem.ticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pacinho.cinemabookingsystem.ticket.model.dto.TicketInfoDto;
import pl.pacinho.cinemabookingsystem.ticket.service.TicketService;

@RequestMapping("/ticket")
@RequiredArgsConstructor
@RestController
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{uuid}")
    ResponseEntity<TicketInfoDto> getTicketInfo(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(
                ticketService.getTicketInfo(uuid)
        );
    }
}
