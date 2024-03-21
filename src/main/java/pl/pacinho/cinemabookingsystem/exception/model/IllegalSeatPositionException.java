package pl.pacinho.cinemabookingsystem.exception.model;

public class IllegalSeatPositionException extends IllegalArgumentException{
    public IllegalSeatPositionException() {
        super("Illegal seat position!");
    }
}
