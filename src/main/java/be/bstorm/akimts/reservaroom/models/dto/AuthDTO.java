package be.bstorm.akimts.reservaroom.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDTO {

    private String username;
    private String token;

}
