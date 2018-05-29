/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userService

import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.entities.Service
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors


/*

  @author Altysh
  Created on May 29, 2018
*/
@Component
class UserServiceImpl : UserServicesInterFace {
    @Autowired
    lateinit var userInterface: UserInterFace;

    override fun addServiceToUser(userId: Int?, service: Service?): Boolean? {
        var user: UserTable? = userInterface.getUser(userId);
        val bool = user?.addService(service)
        userInterface.updateUser(user)
        return bool
    }


    override fun disableServiceForUser(userId: Int?, service: Service?, forced: Boolean?): Boolean? {
        throw UnsupportedOperationException()
    }

    override fun getUserServices(userId: Int?): MutableList<Service>? {
        var user: UserTable? = userInterface.getUser(userId);
        return user?.getServiceCollection()?.stream()?.collect(Collectors.toList())
    }

    override fun removeServiceForUser(userId: Int?, service: Service?, forced: Boolean?): Boolean? {
        var user: UserTable? = userInterface.getUser(userId);
        val bool = user?.removeService(service)
        userInterface.updateUser(user)
        return bool
    }
}