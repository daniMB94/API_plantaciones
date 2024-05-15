package com.example.api_plantanciones.service;

import com.example.api_plantanciones.dto.UserRegisterDTO;
import com.example.api_plantanciones.model.UserAuthority;
import com.example.api_plantanciones.model.UserEntity;
import com.example.api_plantanciones.repository.UserEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityService {

    private final UserEntityRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserEntityService(UserEntityRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findByUserName(String userName) {
        return this.repository.findByUserName(userName);
    }
    public UserEntity save(UserRegisterDTO userDTO) {
        UserEntity user = new UserEntity(
                null,
                userDTO.userName(),
                passwordEncoder.encode(userDTO.password()),
                userDTO.email(),
                List.of(UserAuthority.READ)
        );
        return this.repository.save(user);
    }
}
