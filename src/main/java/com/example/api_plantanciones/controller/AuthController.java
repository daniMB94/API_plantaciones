package com.example.api_plantanciones.controller;

import com.example.api_plantanciones.dto.LoginRequest;
import com.example.api_plantanciones.dto.LoginResponse;
import com.example.api_plantanciones.dto.UserRegisterDTO;
import com.example.api_plantanciones.model.UserEntity;
import com.example.api_plantanciones.security.JwtTokenProvider;
import com.example.api_plantanciones.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserEntityService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/auth/register")
    public UserEntity save(@RequestBody UserRegisterDTO userDTO){
        return this.userService.save(userDTO);
    }


    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest loginDTO){
        Authentication authDTO = new UsernamePasswordAuthenticationToken(loginDTO.userName(), loginDTO.password());

        Authentication authentication = this.authManager.authenticate(authDTO);

        UserEntity user = (UserEntity) authentication.getPrincipal();

        String token = this.jwtTokenProvider.generateToken(authentication);

        return new LoginResponse(user.getUserName(),
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
                token);
    }
}
