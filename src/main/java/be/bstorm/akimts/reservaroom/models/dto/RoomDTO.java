package be.bstorm.akimts.reservaroom.models.dto;

import be.bstorm.akimts.reservaroom.models.entity.Room;
import lombok.Data;

import java.util.List;

@Data
public class RoomDTO {

    private long id;
    private long number;
    private int capacity;
    private List<EquipmentDTO> equipments;
    private List<BookingDTO> bookings;

    public static RoomDTO from(Room entity){
        if(entity == null)
            return null;

        RoomDTO dto = new RoomDTO();
        dto.setId( entity.getId() );
        dto.setNumber( entity.getNumber() );
        dto.setCapacity( entity.getCapacity() );
        dto.setEquipments( entity.getEquipments().stream().map(EquipmentDTO::from).toList() );
        dto.setBookings( entity.getBookings().stream().map(BookingDTO::from).toList() );
        return dto;

    }
}
