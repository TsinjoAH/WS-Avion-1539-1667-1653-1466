package com.example.gestionflotte.controllers;

import com.example.gestionflotte.controllers.common.CrudController;
import com.example.gestionflotte.exception.CustomValidation;
import com.example.gestionflotte.models.User;
import com.example.gestionflotte.responses.AuthenticatedDetails;
import com.example.gestionflotte.responses.Success;
import com.example.gestionflotte.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET})
public class UserController {

    private final LoginService loginService;

    public UserController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Success> login (@RequestBody User user) throws CustomValidation {
        AuthenticatedDetails details = this.loginService.login(user);
        return CrudController.returnSuccess(details, org.springframework.http.HttpStatus.OK);
    }

    @PutMapping("/logout")
    public ResponseEntity<Success> logout (@RequestBody @Valid AuthenticatedDetails details) throws CustomValidation {
        this.loginService.deConnect(details.getToken());
        return CrudController.returnSuccess("success", org.springframework.http.HttpStatus.OK);
    }

}
