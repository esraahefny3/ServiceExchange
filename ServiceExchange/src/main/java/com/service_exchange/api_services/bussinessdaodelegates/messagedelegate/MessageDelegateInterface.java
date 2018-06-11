/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.messagedelegate;

import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.MessagePrivateDto;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.Message;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 *
 * @author esraa
 */
public interface MessageDelegateInterface {
    

    public MessagePrivateDto sendPrivateMessage(Integer senderId, Integer recieverId, Message message);
    public List <MessagePrivateDto>getAllPrivateChatMessages(Integer user1Id,Integer user2Id,Integer pageNum);
    
    public MessageGeneralDto userSendComplaintMessage(Integer senderId, Integer ComplaintId,Integer transactionId, Message message);
    public MessageGeneralDto adminSendComplaintMessage(Integer complaintId,Integer transactionId, Message message);
    public List <MessageGeneralDto>getAllComplaintMessages(Integer complaintId,Integer pageNum);
  
    public MessageGeneralDto sendTransactionMessage(Integer senderId , Integer recieverId, Message message, Integer TransactionId);
    public List <MessageGeneralDto>getAllTransactionMessages(Integer transactionId,Integer pageNum);
    public List<Integer>getUserTransactionIdsList(Integer userId,Integer pageNum);

    public int updateAllTransactionMessagesSeenState(Short notSeenState, Short isSeen, Date seenDate, TransactionInfo transactionId, UserTable senderId);
    public int updateAllComplaintMessagesSeenState(Short notSeenState, Short isSeen, Date seenDate, Complaint complaintId, UserTable senderId);

    public boolean checkUsersInTransaction(Integer senderId,Integer receiverId,TransactionInfo transactionInfo);
    public List<MessageGeneralDto> selectAllUserUnreadedMessages(Integer userId);
    public List<MessageGeneralDto> selectAllUserUnreadedMessages(Integer userId,Integer pageNumbe);
}
