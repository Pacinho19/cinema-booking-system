package pl.pacinho.cinemabookingsystem.ticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pacinho.cinemabookingsystem.ticket.model.dto.TicketInfoDto;
import pl.pacinho.cinemabookingsystem.ticket.payment.model.dto.TicketPaymentRequestDto;
import pl.pacinho.cinemabookingsystem.ticket.payment.service.TicketPaymentService;
import pl.pacinho.cinemabookingsystem.ticket.service.TicketService;

@RequestMapping("/ticket")
@RequiredArgsConstructor
@RestController
public class TicketController {

    private final TicketService ticketService;
    private final TicketPaymentService ticketPaymentService;

    @GetMapping("/{uuid}")
    ResponseEntity<TicketInfoDto> getTicketInfo(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(
                ticketService.getTicketInfo(uuid)
        );
    }

    @PostMapping("/{uuid}/pay")
    ResponseEntity<?> payTicket(@PathVariable("uuid") String uuid, @RequestBody TicketPaymentRequestDto ticketPaymentRequest) {
        ticketPaymentService.pay(uuid, ticketPaymentRequest);
        return ResponseEntity.noContent().build();
    }
}
