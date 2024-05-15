package com.sau.dims.controller;

import com.sau.dims.dto.AuthResponseDTO;
import com.sau.dims.dto.LoginDTO;
import com.sau.dims.dto.UserDTO;
import com.sau.dims.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

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

    @GetMapping("/")
    public String getHome(){
        return "auth";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about/index";
    }


}
