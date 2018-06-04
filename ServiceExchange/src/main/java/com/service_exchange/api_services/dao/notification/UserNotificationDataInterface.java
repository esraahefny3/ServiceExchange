/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.notification;


import com.service_exchange.entities.UserNotification;
import com.service_exchange.entities.UserNotificationPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nouran
 */
@Repository
public interface UserNotificationDataInterface extends CrudRepository<UserNotification, UserNotificationPK> {

    List<UserNotification> findByUserNotificationPK_UserId(Integer userId);

    List<UserNotification> findByNotificationId(Integer notificationId);
}
