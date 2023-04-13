package be.bstorm.akimts.reservaroom.models.dto;

import be.bstorm.akimts.reservaroom.models.BookingStatus;
import be.bstorm.akimts.reservaroom.models.entity.Booking;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingDTO {

    private long id;
    private LocalDate date;
    private LocalTime beginsAt;
    private LocalTime endsAt;
    private String username;
    private BookingStatus status;

    public static BookingDTO from(Booking entity){
        if(entity == null)
            return null;

        BookingDTO dto = new BookingDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setUsername(entity.getBookedBy().getUsername());
        dto.setBeginsAt(entity.getBeginsAt());
        dto.setEndsAt(entity.getEndsAt());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
