package pl.pacinho.cinemabookingsystem.ticket.payment.model.dto;

import java.math.BigDecimal;

public record TicketPaymentRequestDto(BigDecimal price) {
}
