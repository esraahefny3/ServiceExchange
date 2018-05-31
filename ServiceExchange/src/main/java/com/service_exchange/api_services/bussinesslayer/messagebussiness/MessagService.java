/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer.messagebussiness;

import com.service_exchange.api_services.bussinessdaodelegates.messagedelegate.MessageDelegateInterface;
import com.service_exchange.api_services.dao.message.messagedtos.MessagePrivateDto;
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
    public List<Message> getAllPrivateChatMessages(Integer user1Id, Integer user2Id, Integer pageNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message userSendComplaintMessage(Integer senderId, Integer ComplaintId, Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message adminSendComplaintMessage(Integer adminId, Integer ComplaintId, Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> getAllComplaintMessages(Integer complaintId, Integer pageNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message sendTransactionMessage(Integer senderId, Integer recieverId, Message message, Integer TransactionId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> getAllTransactionMessages(Integer transactionId, Integer pageNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
