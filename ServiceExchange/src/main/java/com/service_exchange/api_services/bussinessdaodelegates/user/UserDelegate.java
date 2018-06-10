package com.service_exchange.api_services.bussinessdaodelegates.user;

import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDelegate implements UserDelegateInterface {
    @Autowired
    private UserDataInterFace userDataInterFace;
    @Override
    public UserTable getUserById(Integer userId) {
        Optional<UserTable>userTableOptional=userDataInterFace.findById(userId);
        return userTableOptional.orElse(null);
    }
}
