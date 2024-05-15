package com.sau.dims.controller;

import com.sau.dims.dto.AuthResponseDTO;
import com.sau.dims.dto.LoginDTO;
import com.sau.dims.dto.UserDTO;
import com.sau.dims.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("register")
    @ResponseBody
    public String register(UserDTO user) {
        return authService.register(user);
    }


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<AuthResponseDTO> login(LoginDTO loginDTO){

        AuthResponseDTO authResponseDTO = authService.login(loginDTO);
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);

    }

    // to do: login sonucu bu tokenı al. istek atılırken header kısmına bu tokenı koy. istek atılan methodda bu headerı kontrol et. önce isValid sonra Rol kontrolü

}
