/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.restcontrollers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service_exchange.api_services.bussinesslayer.NotificationService;
import com.service_exchange.api_services.dao.notification.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nouran
 */
@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @RequestMapping(method = RequestMethod.GET, value = "/notifications/{userId}")
    private List<NotificationDto> getAllUserNotificaions(@PathVariable("userId") Integer userId) {
        if (userId != null) {
            return notificationService.getAllUserNotifications(userId);

        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notifications/makeNotification")
    private NotificationDto createNotification(@RequestBody NotificationDto notificationDto) {

        return notificationService.createNotification(notificationDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notifications/send")
    private boolean sendNotificationToUser(@RequestBody String myJson) {
        try {

            if (myJson != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(myJson);

                NotificationDto notificationDto = mapper.convertValue(node.get("notificationDto"), NotificationDto.class);

                List<Integer> usersIds = mapper.convertValue(node.get("users"), List.class);

                notificationService.sendNotificationToUser(notificationDto, usersIds);

                return true;
            }

        } catch (IOException ex) {
            Logger.getLogger(NotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
