package org.ecommerce.account.service.impl;

import org.ecommerce.account.dao.UsersDao;
import org.ecommerce.account.dto.user.UserRegisterRequest;
import org.ecommerce.account.dto.user.UserRegisterResponse;
import org.ecommerce.account.model.Users;
import org.ecommerce.account.service.UsersService;
import org.ecommerce.common.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.ecommerce.common.utils.ApiStatusCode.SUCCESS;

@Service
public class UsersServiceImpl implements UsersService {

    private final static Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    private UsersDao usersDao;

    public ResponseEntity<ApiResponse<UserRegisterResponse>> register(UserRegisterRequest request) {
        try {
            Users user = new Users()
                    .setUserId(UUID.randomUUID().toString())
                    .setUserAccount(request.getUserAccount())
                    .setUserName(request.getUserName())
                    .setPasswordHash(hashPassword(request.getPassword()))
                    .setEmail(request.getEmail())
                    .setPhone(request.getPhone())
                    .setIsActive(true)
                    .setEmailVerified(false)
                    .setPhoneVerified(false);
            Users savedUser = usersDao.save(user);

            UserRegisterResponse response = new UserRegisterResponse()
                    .setUserId(savedUser.getUserId())
                    .setUserAccount(savedUser.getUserAccount())
                    .setUserName(savedUser.getUserName())
                    .setIsActive(savedUser.getIsActive())
                    .setCreatedAt(savedUser.getCreatedAt());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(SUCCESS, "User registered successfully", response));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null));
        }
    }

    private String hashPassword(String password) {
        return password;
    }

}
