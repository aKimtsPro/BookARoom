package be.bstorm.akimts.reservaroom.controller;

import be.bstorm.akimts.reservaroom.models.dto.AuthDTO;
import be.bstorm.akimts.reservaroom.models.form.LoginForm;
import be.bstorm.akimts.reservaroom.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthDTO login(@RequestBody LoginForm form){
        return authService.login(form);
    }
}
