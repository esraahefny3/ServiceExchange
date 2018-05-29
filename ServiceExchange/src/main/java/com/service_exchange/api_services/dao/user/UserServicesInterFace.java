/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user;

import com.service_exchange.entities.Service;
import java.util.List;

/**
 *
 * @author Altysh
 */
public interface UserServicesInterFace {
     List<Service> getUserServices(Integer userId);

    Boolean addServiceToUser(Integer userId, Service service);

    Boolean removeServiceForUser(Integer userId, Service service, Boolean forced);
    Boolean disableServiceForUser(Integer userId, Service service, Boolean forced);
}
