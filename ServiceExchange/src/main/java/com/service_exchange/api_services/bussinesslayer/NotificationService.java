/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service_exchange.api_services.dao.notification.NotificationDataInterface;
import com.service_exchange.api_services.dao.notification.NotificationDto;
import com.service_exchange.api_services.dao.notification.UserNotificationDataInterface;
import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.Notification;
import com.service_exchange.entities.UserNotification;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Nouran
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationDataInterface notificationDataInterface;

    @Autowired
    private UserNotificationDataInterface userNotificationDataInterface;

    @Autowired
    private UserDataInterFace userDataInterFace;

    public List<NotificationDto> getAllUserNotifications(Integer userId) {
        List<NotificationDto> notifications = new ArrayList<>();
        List<UserNotification> userNotifications = new ArrayList<>(userNotificationDataInterface.findByUserNotificationPK_UserId(userId));

        for (UserNotification userNotification : userNotifications) {
            Notification notification = userNotification.getNotification();
            NotificationDto notificationDto = AppFactory.mapToDto(notification, NotificationDto.class);
            notificationDto.setSentBy(notification.getSentBy().getEmail());
            notifications.add(notificationDto);
        }
        return notifications;
    }

    public void sendNotificationToUser(NotificationDto notificationDto, List<Integer> usersIds) {

        ObjectMapper mapper = new ObjectMapper();
        notificationDto.setNotifecationDate(new Date());
        Notification notification = mapper.convertValue(notificationDto, Notification.class);
        notificationDataInterface.save(notification);
        notificationDto.setId(notification.getId());

        for (Integer userId : usersIds) {
            if (userDataInterFace.findById(userId).isPresent()) {
                UserTable user = userDataInterFace.findById(userId).get();
                UserNotification userNotification = new UserNotification(userId, notification.getId());
                userNotification.setUserTable(user);
                userNotification.setNotification(notification);
                userNotificationDataInterface.save(userNotification);
            }

        }

    }

    public NotificationDto createNotification(NotificationDto notificationDto) {

        ObjectMapper mapper = new ObjectMapper();
        notificationDto.setNotifecationDate(new Date());
        Notification notification = mapper.convertValue(notificationDto, Notification.class);
        notificationDataInterface.save(notification);
        String adminEmail = notificationDto.getSentBy();
        notificationDto = AppFactory.mapToDto(notification, NotificationDto.class);
        notificationDto.setSentBy(adminEmail);
        return notificationDto;

    }

}
