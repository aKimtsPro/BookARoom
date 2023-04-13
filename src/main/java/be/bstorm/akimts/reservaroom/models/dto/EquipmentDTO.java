package be.bstorm.akimts.reservaroom.models.dto;

import be.bstorm.akimts.reservaroom.models.entity.Equipment;
import lombok.Data;

@Data
public class EquipmentDTO {

    private long id;
    private String name;

    public static EquipmentDTO from(Equipment entity){
        if(entity == null)
            return null;

        EquipmentDTO dto = new EquipmentDTO();
        dto.id = entity.getId();
        dto.name = entity.getName();
        return dto;
    }
}
