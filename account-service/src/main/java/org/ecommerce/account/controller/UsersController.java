package org.ecommerce.account.controller;

import org.ecommerce.account.dto.user.UserRegisterRequest;
import org.ecommerce.account.dto.user.UserRegisterResponse;
import org.ecommerce.account.service.UsersService;
import org.ecommerce.common.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<UserRegisterResponse>> register(@RequestBody @Valid UserRegisterRequest request) {
        return usersService.register(request);
    }

}
