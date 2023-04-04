package com.springbootecommerce.server.accounts.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class UserRegisterDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(max=24,min=8,message="pasword must be between 8 and 24 characters")
    private String password;

    @NotNull
    @Size(max=24,min=8,message="pasword must be between 8 and 24 characters")
    private String passwordConfirmation;
}
