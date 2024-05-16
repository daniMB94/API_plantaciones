package com.example.api_plantanciones.security;

import com.example.api_plantanciones.service.UserEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserEntityService userService;

    public UserDetailsServiceImpl(UserEntityService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUserByUsername {}", username);
        System.out.println(this.userService.findByUserName(username).get().getAuthorities());

        return this.userService.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username + "no encontrado"));
    }
}
