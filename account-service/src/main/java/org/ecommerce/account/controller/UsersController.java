package org.ecommerce.account.controller;

import org.ecommerce.account.dto.user.ReqSocialRegister;
import org.ecommerce.account.dto.user.ReqUsersRegister;
import org.ecommerce.account.model.Users;
import org.ecommerce.account.service.UsersService;
import org.ecommerce.account.util.HttpResult.CommonHttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public CommonHttpResult<Users> register(@RequestBody @Valid ReqUsersRegister reqUsersRegister) {
        return usersService.register(reqUsersRegister);
    }

    @PostMapping("/social-register")
    public CommonHttpResult<Users> socialRegister(@RequestBody @Valid ReqSocialRegister reqSocialRegister) {
        return usersService.socialRegister(reqSocialRegister);
    }

}
