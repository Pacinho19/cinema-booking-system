package pl.pacinho.cinemabookingsystem.exception.model;

public class SeatReservationException extends ConflictException {
    public SeatReservationException() {
        super("Selected seat is already reserved");
    }
}
