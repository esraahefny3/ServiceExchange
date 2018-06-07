/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.messagedelegate;

import com.service_exchange.api_services.dao.dto.MessageComplaintDto;
import com.service_exchange.api_services.dao.dto.MessagePrivateDto;
import com.service_exchange.api_services.dao.dto.MessageTransactionDto;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.Message;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;

import java.util.Date;
import java.util.List;

/**
 *
 * @author esraa
 */
public interface MessageDelegateInterface {
    

    public MessagePrivateDto sendPrivateMessage(Integer senderId, Integer recieverId, Message message);
    public List <MessagePrivateDto>getAllPrivateChatMessages(Integer user1Id,Integer user2Id,Integer pageNum);
    
    public MessageComplaintDto userSendComplaintMessage(Integer senderId, Integer ComplaintId, Message message);
    public MessageComplaintDto adminSendComplaintMessage(Integer complaintId, Message message);
    public List <MessageComplaintDto>getAllComplaintMessages(Integer complaintId,Integer pageNum);
  
    public MessageTransactionDto sendTransactionMessage(Integer senderId , Integer recieverId, Message message, Integer TransactionId);
    public List <MessageTransactionDto>getAllTransactionMessages(Integer transactionId,Integer pageNum);
    public List<Integer>getUserTransactionIdsList(Integer userId,Integer pageNum);

    public int updateAllTransactionMessagesSeenState(Short notSeenState, Short isSeen, Date seenDate, TransactionInfo transactionId, UserTable senderId);
    public int updateAllComplaintMessagesSeenState(Short notSeenState, Short isSeen, Date seenDate, Complaint complaintId, UserTable senderId);

    public boolean checkUsersInTransaction(Integer senderId,Integer receiverId,TransactionInfo transactionInfo);
}
