package be.bstorm.akimts.reservaroom.models;

import lombok.Getter;

@Getter
public enum UserRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String authority;
    UserRole(String authority){
        this.authority = authority;
    }

}
