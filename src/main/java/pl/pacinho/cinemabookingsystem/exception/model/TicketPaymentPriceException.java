package pl.pacinho.cinemabookingsystem.exception.model;

public class TicketPaymentPriceException extends ConflictException {
    public TicketPaymentPriceException() {
        super("Payment price is not equal to ticket price!");
    }
}