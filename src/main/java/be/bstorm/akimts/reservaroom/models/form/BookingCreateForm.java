package be.bstorm.akimts.reservaroom.models.form;

import be.bstorm.akimts.reservaroom.models.entity.Booking;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingCreateForm {

    @Positive
    private long roomId;
    @Positive
    private long userId;
    @Future
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime beginsAt;
    @NotNull
    private LocalTime endsAt;

    public Booking toEntity(){
        Booking booking = new Booking();

        booking.setDate(date);
        booking.setBeginsAt(beginsAt);
        booking.setEndsAt(endsAt);

        return booking;
    }

}
