/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer.messagebussiness;

import com.service_exchange.api_services.bussinessdaodelegates.messagedelegate.MessageDelegateInterface;
import com.service_exchange.api_services.dao.dto.MessageComplaintDto;
import com.service_exchange.api_services.dao.dto.MessagePrivateDto;
import com.service_exchange.api_services.dao.dto.MessageTransactionDto;
import com.service_exchange.entities.Message;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author esraa
 */
@Component
public class MessagService implements MessageServiceInterface{

    @Autowired
    private MessageDelegateInterface messageDelegateInterface;
    @Override
    public MessagePrivateDto sendPrivateMessage(Integer senderId, Integer recieverId, Message message) {
      
        return messageDelegateInterface.sendPrivateMessage(senderId, recieverId, message);
    }

    @Override
    public List<MessagePrivateDto> getAllPrivateChatMessages(Integer user1Id, Integer user2Id, Integer pageNum) {
        return messageDelegateInterface.getAllPrivateChatMessages(user1Id, user2Id, pageNum);
    }

    @Override
    public MessageComplaintDto userSendComplaintMessage(Integer senderId, Integer ComplaintId, Message message) {
        return messageDelegateInterface.userSendComplaintMessage(senderId, ComplaintId, message);
    }

    @Override
    public MessageComplaintDto adminSendComplaintMessage( Integer ComplaintId, Message message) {
       return messageDelegateInterface.adminSendComplaintMessage(ComplaintId, message);
    }

    @Override
    public List<MessageComplaintDto> getAllComplaintMessages(Integer complaintId, Integer pageNum) {
        return messageDelegateInterface.getAllComplaintMessages(complaintId, pageNum);
    }

    @Override
    public MessageTransactionDto sendTransactionMessage(Integer senderId, Integer recieverId, Message message, Integer TransactionId) {
            return messageDelegateInterface.sendTransactionMessage(senderId, recieverId, message, TransactionId);
    }

    @Override
    public List<MessageTransactionDto> getAllTransactionMessages(Integer transactionId, Integer pageNum) {
            return messageDelegateInterface.getAllTransactionMessages(transactionId, pageNum);
    }
     
}
