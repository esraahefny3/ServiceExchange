/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userService

import com.service_exchange.api_services.dao.service.ServiceInterface
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
    @Autowired
    lateinit var serviceInterFace: ServiceInterface

    override fun addServiceToUser(userId: Int?, service: Service?): Service? {
        var user: UserTable? = userInterface.getUser(userId);
        service?.madeBy = user
        val bool = serviceInterFace.createService(service)
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

    override fun removeServiceForUser(userId: Int?, service: Int?, forced: Boolean?): Boolean? {
        var user: UserTable? = userInterface.getUser(userId)
        var serviceEnt: Service? = null
        var bool = false
        if (service != null) {
            serviceEnt = serviceInterFace.getService(service)
            if (serviceEnt != null) {
                if (forced == true) {
                    bool = user?.removeService(serviceEnt) ?: false

                } else if (serviceEnt.transactionInfoCollection.size == 0) {
                    bool = user?.removeService(serviceEnt) ?: false
                }
                if (bool)
                    userInterface.updateUser(user)
            }

        }
        return bool
    }
}