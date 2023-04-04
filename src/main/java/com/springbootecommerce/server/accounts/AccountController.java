package com.springbootecommerce.server.accounts;

import com.springbootecommerce.server.accounts.models.UserLoginDto;
import com.springbootecommerce.server.accounts.models.UserRegisterDto;
import com.springbootecommerce.server.accounts.models.UserResDto;
import com.springbootecommerce.server.accounts.services.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    AppUserServiceImpl userService;

    @Autowired
    public AccountController(AppUserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResDto register(@RequestBody UserRegisterDto userRegisterDto){
        return this.userService.register(userRegisterDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResDto> register(@RequestBody UserLoginDto userLoginDto){
        return new ResponseEntity(this.userService.login(userLoginDto), HttpStatus.OK);
    }

}
