package com.springbootecommerce.server.accounts.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class UserResDto {
    private String username;
    private String token;
}
