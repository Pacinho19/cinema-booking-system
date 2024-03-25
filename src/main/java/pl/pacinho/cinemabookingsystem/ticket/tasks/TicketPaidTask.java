package pl.pacinho.cinemabookingsystem.ticket.tasks;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.pacinho.cinemabookingsystem.ticket.service.TicketService;

import java.time.LocalDateTime;

@Log4j2
@RequiredArgsConstructor
@Component
public class TicketPaidTask {

    private final TicketService ticketService;

    @Scheduled(fixedRate = 1 * 60 * 1_000)
    //Every 5 minutes
    public void cancelAllUnpaidTickets() {
        log.info("Cancelling all unpaid tickets: " + LocalDateTime.now());
        ticketService.cancelAllUnpaidTickets();
    }
}
