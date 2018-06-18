/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.restcontrollers;

import com.service_exchange.api_services.bussinesslayer.messagebussiness.MessageServiceInterface;
import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.MessagePrivateDto;
import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.Message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author esraa
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageServiceInterface messageServiceInterfaceImpl;

    private Integer pageSize = 20;

//    @RequestMapping(value = "/sendPrivateMessage", method = RequestMethod.POST)
//    public MessagePrivateDto sendPrivateMessage(@RequestBody MessagePrivateDto messagePrivateDto) {
//
//        if (messagePrivateDto != null) {
//            Integer senderId = (Integer) messagePrivateDto.getSenderId();
//            Integer recieverId = (Integer) messagePrivateDto.getReceiverId();
//
//            //b7wl l obj fl map lno3o
////            ObjectMapper mapper = new ObjectMapper();
////            Message message = mapper.convertValue(dataMap.get("message"), Message.class);
//            Message message=AppFactory.mapToEntity(messagePrivateDto,Message.class);
//            if (senderId != null && recieverId != null && message != null) {
//                return messageServiceInterfaceImpl.sendPrivateMessage(senderId, recieverId, message);
//            }
//        }
//
//        return null;
//    }
//
//    @RequestMapping(value = "/getAllPrivateChatMessages/{user1Id}/{user2Id}/{pageNum}", method = RequestMethod.GET)
//    public List<MessagePrivateDto> getAllPrivateChatMessages(@PathVariable("user1Id") Integer user1Id, @PathVariable("user2Id") Integer user2Id, @PathVariable("pageNum") Integer pageNum) {
//        if (user1Id != null && user2Id != null && pageNum != null) {
//            return messageServiceInterfaceImpl.getAllPrivateChatMessages(user1Id, user2Id, pageNum);
//        }
//        return null;
//    }

    @RequestMapping(value = "/userSendComplaintMessage", method = RequestMethod.POST)
    public MessageGeneralDto userSendComplaintMessage(@RequestBody MessageGeneralDto messageComplaintDto ) {

        if (messageComplaintDto != null) {
//            Integer senderId = (Integer) dataMap.get("senderId");
//            Integer complaintId = (Integer) dataMap.get("complaintId");
//
//            //b7wl l obj fl map lno3o
//            ObjectMapper mapper = new ObjectMapper();
//            Message message = mapper.convertValue(dataMap.get("message"), Message.class);
            Integer senderId = (Integer) messageComplaintDto.getSenderId();
            Integer complaintId = (Integer) messageComplaintDto.getComplaintId();
            Integer transactionId = (Integer) messageComplaintDto.getTransactionId();
            Message message=AppFactory.mapToEntity(messageComplaintDto,Message.class);
            if (senderId != null && complaintId != null && message != null&& transactionId != null ) {
                return messageServiceInterfaceImpl.userSendComplaintMessage(senderId, complaintId,transactionId, message);
            }
        }

        return null;
    }
    
     @RequestMapping(value = "/adminSendComplaintMessage", method = RequestMethod.POST)
    public MessageGeneralDto adminSendComplaintMessage(@RequestBody MessageGeneralDto messageComplaintDto) {

        if (messageComplaintDto != null) {
            Integer complaintId = (Integer) messageComplaintDto.getComplaintId();
            Integer transactionId = (Integer) messageComplaintDto.getTransactionId();
            //b7wl l obj fl map lno3o
//            ObjectMapper mapper = new ObjectMapper();
//            Message message = mapper.convertValue(dataMap.get("message"), Message.class);
              Message message=AppFactory.mapToEntity(messageComplaintDto,Message.class);
            if ( complaintId != null && message != null&&transactionId!=null&&messageComplaintDto.getSenderId()==null) {
                return messageServiceInterfaceImpl.adminSendComplaintMessage(complaintId,transactionId, message);
            }
        }

        return null;
    }
    
    @RequestMapping(value = "/getAllComplaintMessages/{complaintId}/{userId}/{pageNum}", method = RequestMethod.GET)
    public List<MessageGeneralDto> getAllComplaintMessages(@PathVariable("complaintId") Integer complaintId,@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum) {
        if (complaintId != null && pageNum != null) {
            return messageServiceInterfaceImpl.getAllComplaintMessages(complaintId,userId, pageNum);
        }
        return null;
    }
    @RequestMapping(value = "/sendTransactionMessage", method = RequestMethod.POST)
    public MessageGeneralDto sendTransactionMessage(@RequestBody MessageGeneralDto messageGeneralDto) {

        if (messageGeneralDto != null) {
            Integer senderId = (Integer) messageGeneralDto.getSenderId();
            Integer receiverId = (Integer) messageGeneralDto.getReceiverId();
            Integer transactionId = (Integer) messageGeneralDto.getTransactionId();
            Integer complaintId = (Integer) messageGeneralDto.getComplaintId();
//            //b7wl l obj fl map lno3o
//            ObjectMapper mapper = new ObjectMapper();
//            Message message = mapper.convertValue(dataMap.get("message"), Message.class);
            Message message=AppFactory.mapToEntity(messageGeneralDto,Message.class);
            if ( senderId != null && receiverId != null && transactionId != null && message != null&&complaintId==null&&senderId!=receiverId) {
                return  messageServiceInterfaceImpl.sendTransactionMessage(senderId, receiverId, message, transactionId);

            }
        }

        return null;
    }
    
     @RequestMapping(value = "/getAllTransactionMessages/{transactionId}/{userId}/{pageNum}", method = RequestMethod.GET)
    public List<MessageGeneralDto> getAllTransactionMessages(@PathVariable("transactionId") Integer transactionId,@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum) {
        if (transactionId != null && pageNum != null) {
            return messageServiceInterfaceImpl.getAllTransactionMessages(transactionId,userId, pageNum);
        }
        return null;
    }

    @RequestMapping(value = "/getAllUserTransactionChats/{userId}/{pageNum}", method = RequestMethod.GET)
    public List<TransactionChatDto>  getAllUserTransactionChats(@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum) {
        if (userId != null && pageNum != null) {
            return messageServiceInterfaceImpl.getAllUserTransactionChats(userId,pageNum);
        }
        return null;
    }

    @RequestMapping(value = "/selectAllUserUnreadedMessages/{userId}", method = RequestMethod.GET)
    public List<MessageGeneralDto>  selectAllUserUnreadedMessages(@PathVariable("userId") Integer userId) {
        if (userId != null) {
            return messageServiceInterfaceImpl.selectAllUserUnreadedMessages(userId);
        }
        return null;
    }
    @RequestMapping(value = "/selectAllUserUnreadedMessages/{userId}/{pageNum}", method = RequestMethod.GET)
    public List<MessageGeneralDto>  selectAllUserUnreadedMessages(@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum) {
        if (userId != null && pageNum != null) {
            return messageServiceInterfaceImpl.selectAllUserUnreadedMessages(userId,pageNum);
        }
        return null;
    }
}
