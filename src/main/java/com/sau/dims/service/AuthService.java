package com.sau.dims.service;


import com.sau.dims.dto.AuthResponseDTO;
import com.sau.dims.dto.LoginDTO;
import com.sau.dims.dto.UserDTO;

public interface AuthService {

    String register(UserDTO requestDto);

    AuthResponseDTO login(LoginDTO loginDTO);
}
