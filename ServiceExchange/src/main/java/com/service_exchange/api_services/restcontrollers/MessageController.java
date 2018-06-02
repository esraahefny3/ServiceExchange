/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service_exchange.api_services.bussinessdaodelegates.messagedelegate.MessageDelegateInterface;
import com.service_exchange.api_services.bussinesslayer.BadgeService;
import com.service_exchange.api_services.bussinesslayer.messagebussiness.MessageServiceInterface;
import com.service_exchange.api_services.dao.message.messagedtos.MessageComplaintDto;
import com.service_exchange.api_services.dao.message.messagedtos.MessagePrivateDto;
import com.service_exchange.api_services.dao.message.messagedtos.MessageTransactionDto;
import com.service_exchange.entities.Badge;
import com.service_exchange.entities.Message;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author esraa
 */
@RestController
public class MessageController {

    @Autowired
    private MessageServiceInterface messageServiceInterfaceImpl;

    private Integer pageSize = 20;

    @RequestMapping(value = "/sendPrivateMessage", method = RequestMethod.POST)
    public MessagePrivateDto sendPrivateMessage(@RequestBody Map<String, Object> dataMap) {

        if (dataMap != null) {
            Integer senderId = (Integer) dataMap.get("senderId");
            Integer recieverId = (Integer) dataMap.get("recieverId");

            //b7wl l obj fl map lno3o
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.convertValue(dataMap.get("message"), Message.class);
            if (senderId != null && recieverId != null && message != null) {
                return messageServiceInterfaceImpl.sendPrivateMessage(senderId, recieverId, message);
            }
        }

        return null;
    }

    @RequestMapping(value = "/getAllPrivateChatMessages/{user1Id}/{user2Id}/{pageNum}", method = RequestMethod.GET)
    public List<MessagePrivateDto> getAllPrivateChatMessages(@PathVariable("user1Id") Integer user1Id, @PathVariable("user2Id") Integer user2Id, @PathVariable("pageNum") Integer pageNum) {
        if (user1Id != null && user2Id != null && pageNum != null) {
            return messageServiceInterfaceImpl.getAllPrivateChatMessages(user1Id, user2Id, pageNum);
        }
        return null;
    }

    @RequestMapping(value = "/userSendComplaintMessage", method = RequestMethod.POST)
    public MessageComplaintDto userSendComplaintMessage(@RequestBody Map<String, Object> dataMap) {

        if (dataMap != null) {
            Integer senderId = (Integer) dataMap.get("senderId");
            Integer complaintId = (Integer) dataMap.get("complaintId");

            //b7wl l obj fl map lno3o
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.convertValue(dataMap.get("message"), Message.class);
            if (senderId != null && complaintId != null && message != null) {
                return messageServiceInterfaceImpl.userSendComplaintMessage(senderId, complaintId, message);
            }
        }

        return null;
    }
    
     @RequestMapping(value = "/adminSendComplaintMessage", method = RequestMethod.POST)
    public MessageComplaintDto adminSendComplaintMessage(@RequestBody Map<String, Object> dataMap) {

        if (dataMap != null) {
            Integer complaintId = (Integer) dataMap.get("complaintId");

            //b7wl l obj fl map lno3o
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.convertValue(dataMap.get("message"), Message.class);
            if ( complaintId != null && message != null) {
                return messageServiceInterfaceImpl.adminSendComplaintMessage(complaintId, message);
            }
        }

        return null;
    }
    
    @RequestMapping(value = "/getAllComplaintMessages/{complaintId}/{pageNum}", method = RequestMethod.GET)
    public List<MessageComplaintDto> getAllComplaintMessages(@PathVariable("complaintId") Integer complaintId, @PathVariable("pageNum") Integer pageNum) {
        if (complaintId != null && pageNum != null) {
            return messageServiceInterfaceImpl.getAllComplaintMessages(complaintId, pageNum);
        }
        return null;
    }
    @RequestMapping(value = "/sendTransactionMessage", method = RequestMethod.POST)
    public MessageTransactionDto sendTransactionMessage(@RequestBody Map<String, Object> dataMap) {

        if (dataMap != null) {
            Integer senderId = (Integer) dataMap.get("senderId");
            Integer receiverId = (Integer) dataMap.get("receiverId");
            Integer transactionId = (Integer) dataMap.get("transactionId");

            //b7wl l obj fl map lno3o
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.convertValue(dataMap.get("message"), Message.class);
            if ( senderId != null && receiverId != null && transactionId != null && message != null) {
                return messageServiceInterfaceImpl.sendTransactionMessage(senderId, receiverId, message, transactionId);
            }
        }

        return null;
    }
    
     @RequestMapping(value = "/getAllTransactionMessages/{transactio nId}/{pageNum}", method = RequestMethod.GET)
    public List<MessageTransactionDto> getAllTransactionMessages(@PathVariable("transactionId") Integer transactionId, @PathVariable("pageNum") Integer pageNum) {
        if (transactionId != null && pageNum != null) {
            return messageServiceInterfaceImpl.getAllTransactionMessages(transactionId, pageNum);
        }
        return null;
    }
}
