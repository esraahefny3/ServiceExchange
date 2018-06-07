package com.service_exchange.api_services.bussinessdaodelegates.user;

import com.service_exchange.entities.UserTable;

public interface UserDelegateInterface {
    public UserTable getUserById(Integer userId);
}
