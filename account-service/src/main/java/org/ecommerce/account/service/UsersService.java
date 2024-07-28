package org.ecommerce.account.service;


import org.ecommerce.account.dto.user.ReqSocialRegister;
import org.ecommerce.account.dto.user.ReqUsersRegister;
import org.ecommerce.account.model.Users;
import org.ecommerce.account.util.HttpResult.CommonHttpResult;

public interface UsersService {

    CommonHttpResult<Users> register(ReqUsersRegister reqUsersRegister);

    CommonHttpResult<Users> socialRegister(ReqSocialRegister reqSocialRegister);

}
