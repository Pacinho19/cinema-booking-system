package pl.pacinho.cinemabookingsystem.ticket.payment.repository;

import pl.pacinho.cinemabookingsystem.ticket.payment.model.entity.TicketPayment;

public interface TicketPaymentRepository {

    TicketPayment save(TicketPayment ticketPayment);

}
