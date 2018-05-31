/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer.messagebussiness;

import com.service_exchange.api_services.dao.message.messagedtos.MessagePrivateDto;
import com.service_exchange.entities.Message;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author esraa
 */

public interface MessageServiceInterface {
    public MessagePrivateDto sendPrivateMessage(Integer senderId,Integer recieverId,Message message);
    public List <Message>getAllPrivateChatMessages(Integer user1Id,Integer user2Id,Integer pageNum);
    
    public Message userSendComplaintMessage(Integer senderId,Integer ComplaintId,Message message);
    public Message adminSendComplaintMessage(Integer adminId,Integer ComplaintId,Message message);
    public List <Message>getAllComplaintMessages(Integer complaintId,Integer pageNum);
  
    public Message sendTransactionMessage(Integer senderId ,Integer recieverId,Message message,Integer TransactionId);
    public List <Message>getAllTransactionMessages(Integer transactionId,Integer pageNum);
    
   
}
