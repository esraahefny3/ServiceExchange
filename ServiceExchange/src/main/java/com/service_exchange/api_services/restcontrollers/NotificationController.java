/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.restcontrollers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service_exchange.api_services.bussinesslayer.NotificationService;
import com.service_exchange.api_services.dao.dto.NotificationDto;
import com.service_exchange.utal.firebasenotificationsutil.FirebaseNotificationMessageMaker;
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

    @RequestMapping(method = RequestMethod.POST, value = "/notifications/sendFirebase")
    private boolean sendNotificationToUserFirebase(@RequestBody NotificationDto notificationDto) {
        try {

//            if (myJson != null) {
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode node = mapper.readTree(myJson);
//
//                NotificationDto notificationDto = mapper.convertValue(node.get("notificationDto"), NotificationDto.class);
//
//                List<Integer> usersIds = mapper.convertValue(node.get("users"), List.class);
//
//                notificationService.sendNotificationToUser(notificationDto, usersIds);
            String user="d6VMhwiEjAY:APA91bH6bcdMBsKig1Y5f7354wcbW0zZsAAqaQnU_7vo8xPoAzUCLF1Y8KViFG_nDUcE70w-XAwXFtUFb20JntCagBr3iPEy4LtbaCaZFqPk-RFMgTHSpjncJiusWWjubjHZIenZ2cPm";
            FirebaseNotificationMessageMaker.sendFirebaseNotificationMessageToUserTry(notificationDto,user);
                return true;


        } catch (Exception ex) {
            Logger.getLogger(NotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
