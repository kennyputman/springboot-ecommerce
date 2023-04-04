package com.springbootecommerce.server.accounts.services;

import com.springbootecommerce.server.accounts.models.AppUser;
import com.springbootecommerce.server.accounts.models.UserLoginDto;
import com.springbootecommerce.server.accounts.models.UserRegisterDto;
import com.springbootecommerce.server.accounts.models.UserResDto;

public interface AppUserService {

    UserResDto register(UserRegisterDto userRegisterDto);
    UserResDto login(UserLoginDto userLoginDto);

}
