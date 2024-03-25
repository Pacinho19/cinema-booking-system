package pl.pacinho.cinemabookingsystem.exception.model;

public class TicketCancelledException extends ConflictException {
    public TicketCancelledException() {
        super("Ticket is currently cancelled! The time to pay has passed!");
    }
}
