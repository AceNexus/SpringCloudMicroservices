package org.ecommerce.account.service;

import org.ecommerce.account.dto.user.UserRegisterRequest;
import org.ecommerce.account.dto.user.UserRegisterResponse;
import org.ecommerce.common.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface UsersService {

    ResponseEntity<ApiResponse<UserRegisterResponse>> register(UserRegisterRequest request);

}