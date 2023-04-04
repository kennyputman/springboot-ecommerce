package com.springbootecommerce.server.accounts.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserLoginDto {

    @NotNull
    private String username;

    @NotNull
    @Size(max=24,min=8,message="pasword must be between 8 and 24 characters")
    private String password;
}
