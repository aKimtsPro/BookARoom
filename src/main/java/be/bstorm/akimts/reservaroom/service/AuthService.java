package be.bstorm.akimts.reservaroom.service;

import be.bstorm.akimts.reservaroom.models.dto.AuthDTO;
import be.bstorm.akimts.reservaroom.models.form.LoginForm;

public interface AuthService {

    AuthDTO login(LoginForm form);

}
