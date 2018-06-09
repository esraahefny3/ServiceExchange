/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userService;

import com.service_exchange.entities.Service;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Altysh
 */
public interface UserServicesInterFace {
    @Nullable
    List<Service> getUserServices(Integer userId);

    Service addServiceToUser(Integer userId, Service service);

    Boolean removeServiceForUser(Integer userId, Integer serviceId, Boolean forced);

    Boolean disableServiceForUser(Integer userId, Service service, Boolean forced);
}


