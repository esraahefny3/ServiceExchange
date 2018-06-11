/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer.messagebussiness;

import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.MessagePrivateDto;
import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.service_exchange.entities.Message;
import java.util.List;

/**
 *
 * @author esraa
 */

public interface MessageServiceInterface {
    public MessagePrivateDto sendPrivateMessage(Integer senderId,Integer recieverId,Message message);
    public List <MessagePrivateDto>getAllPrivateChatMessages(Integer user1Id, Integer user2Id, Integer pageNum);
    
    public MessageGeneralDto userSendComplaintMessage(Integer senderId, Integer ComplaintId,Integer transactionId, Message message);
    public MessageGeneralDto adminSendComplaintMessage(Integer ComplaintId,Integer transactionId,Message message);
    public List <MessageGeneralDto>getAllComplaintMessages(Integer complaintId,Integer senderId,Integer pageNum);
  
    public MessageGeneralDto sendTransactionMessage(Integer senderId , Integer recieverId, Message message, Integer TransactionId);
    public List <MessageGeneralDto>getAllTransactionMessages(Integer transactionId,Integer senderId,Integer pageNum);

    public List<TransactionChatDto>getAllUserTransactionChats(Integer userId,Integer pageNum);
    public List<MessageGeneralDto> selectAllUserUnreadedMessages(Integer userId);
    public List<MessageGeneralDto> selectAllUserUnreadedMessages(Integer userId,Integer pageNumber);
}
