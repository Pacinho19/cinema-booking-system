package pl.pacinho.cinemabookingsystem.ticket.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.pacinho.cinemabookingsystem.ticket.service.TicketService;

@RequiredArgsConstructor
@Component
public class TicketPaidTask {

    private final TicketService ticketService;

    @Scheduled(fixedRate = 5 * 60 * 1_000)
    //Every 5 minutes
    public void deleteAllUnpaidTickets() {
        ticketService.deleteAllUnpaidTickets();
    }
}
