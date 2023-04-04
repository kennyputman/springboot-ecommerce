package com.springbootecommerce.server.accounts.services;

import com.springbootecommerce.server.accounts.models.*;
import com.springbootecommerce.server.accounts.repository.AppUserRepository;
import com.springbootecommerce.server.accounts.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    AppUserRepository appUserRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;


    @Autowired
    public AppUserServiceImpl(AppUserRepository repo, PasswordEncoder passwordEncoder,
                              RoleRepository roleRepository, AuthenticationManager authenticationManager) {
        this.appUserRepository = repo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResDto register(UserRegisterDto userRegisterDto) {

        if(this.appUserRepository.findByUsername(userRegisterDto.getUsername()).isPresent()){
            throw new RuntimeException("user with username already exisits");
        };


        AppUser user = AppUser.builder()
                .email(userRegisterDto.getEmail())
                .username(userRegisterDto.getUsername())
                .firstName(userRegisterDto.getFirstName())
                .lastName(userRegisterDto.getLastName())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .build();

        Role roles = roleRepository.findRoleByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        AppUser newUser = this.appUserRepository.save(user);

        return new UserResDto(newUser.getUsername(), "somesecrettoken");
    }

    @Override
    public UserResDto login(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // is this necessary?
        Optional<AppUser> user = this.appUserRepository.findByUsername(userLoginDto.getUsername());
        return new UserResDto(user.get().getUsername(), "secretlogintoken");
    }
}
