package org.ecommerce.account.service.impl;

import org.ecommerce.account.dao.UsersDao;
import org.ecommerce.account.dto.user.ReqSocialRegister;
import org.ecommerce.account.dto.user.ReqUsersRegister;
import org.ecommerce.account.model.Users;
import org.ecommerce.account.service.UsersService;
import org.ecommerce.account.util.HttpResult.CommonHttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersServiceImpl implements UsersService {

    private final static Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    private UsersDao usersDao;

    @Transactional
    @Override
    public CommonHttpResult<Users> register(ReqUsersRegister reqUsersRegister) {
        Users newUser = new Users();
        BeanUtils.copyProperties(reqUsersRegister, newUser);
        newUser = usersDao.saveAndFlush(newUser);
        return new CommonHttpResult<>(0, newUser);
    }

    @Override
    public CommonHttpResult<Users> socialRegister(ReqSocialRegister reqSocialRegister) {
        return new CommonHttpResult<>(0, null);
    }

}
