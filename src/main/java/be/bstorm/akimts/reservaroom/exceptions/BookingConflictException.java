package be.bstorm.akimts.reservaroom.exceptions;

public class BookingConflictException extends RuntimeException {

    public BookingConflictException() {
        super("Impossible car la un/des réservation.s sont en conflit avec celle-ci.");
    }
}
