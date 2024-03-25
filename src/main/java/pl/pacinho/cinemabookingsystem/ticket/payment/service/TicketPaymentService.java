package pl.pacinho.cinemabookingsystem.ticket.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.cinemabookingsystem.exception.model.TicketCancelledException;
import pl.pacinho.cinemabookingsystem.exception.model.TicketPaymentPriceException;
import pl.pacinho.cinemabookingsystem.screening.seat.model.enums.SeatState;
import pl.pacinho.cinemabookingsystem.ticket.model.entity.Ticket;
import pl.pacinho.cinemabookingsystem.ticket.model.enums.TicketState;
import pl.pacinho.cinemabookingsystem.ticket.payment.model.dto.TicketPaymentRequestDto;
import pl.pacinho.cinemabookingsystem.ticket.payment.model.entity.TicketPayment;
import pl.pacinho.cinemabookingsystem.ticket.payment.repository.TicketPaymentRepository;
import pl.pacinho.cinemabookingsystem.ticket.repository.TicketRepository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class TicketPaymentService {

    private final TicketRepository ticketRepository;
    private final TicketPaymentRepository ticketPaymentRepository;

    public void pay(String uuid, TicketPaymentRequestDto ticketPaymentRequest) {
        Ticket ticket = ticketRepository.findByUuidWithFetchSeat(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Could not find ticket with given identifier: " + uuid));

        if(ticket.getState()==TicketState.CANCELLED)
            throw new TicketCancelledException();

        if (ticket.getPrice().compareTo(ticketPaymentRequest.price()) != 0)
            throw new TicketPaymentPriceException();

        ticket.setState(TicketState.PAID);
        ticket.getScreeningSeat().setState(SeatState.UNAVAILABLE);
        ticketPaymentRepository.save(
                new TicketPayment(ticket)
        );
    }
}
