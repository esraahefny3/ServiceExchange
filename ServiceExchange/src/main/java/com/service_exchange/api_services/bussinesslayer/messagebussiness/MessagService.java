/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer.messagebussiness;

import com.service_exchange.api_services.bussinessdaodelegates.complaintdelegate.ComplaintDelegateInterface;
import com.service_exchange.api_services.bussinessdaodelegates.messagedelegate.MessageDelegateInterface;
import com.service_exchange.api_services.bussinessdaodelegates.transaction.TransactionDelegateInterface;
import com.service_exchange.api_services.bussinessdaodelegates.user.UserDelegateInterface;
import com.service_exchange.api_services.dao.dto.MessageComplaintDto;
import com.service_exchange.api_services.dao.dto.MessagePrivateDto;
import com.service_exchange.api_services.dao.dto.MessageTransactionDto;
import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
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

    @Autowired
    private ComplaintDelegateInterface complaintDelegateInterfaceImpl;


    @Autowired
    private TransactionDelegateInterface transactionDelegateInterfaceImpl;
    @Autowired
    private UserDelegateInterface userDelegateInterfaceImpl;
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
    public List<MessageComplaintDto> getAllComplaintMessages(Integer complaintId,Integer senderId, Integer pageNum) {
        Short notSeenState=0;
        Short isSeen=1;
        Complaint complaint=complaintDelegateInterfaceImpl.checkComplaintExsistById(complaintId);
        UserTable user=complaint.getUserId();
        if(complaint!=null&& user.getId()==senderId) {//l complaint mwgoda w l user hoa l 3mlha
            messageDelegateInterface.updateAllComplaintMessagesSeenState(notSeenState, isSeen, new Date(), complaint, user);
            return messageDelegateInterface.getAllComplaintMessages(complaintId, pageNum);
        }
        else{
            return null;
        }
    }

    @Override
    public MessageTransactionDto sendTransactionMessage(Integer senderId, Integer recieverId, Message message, Integer TransactionId) {
            return messageDelegateInterface.sendTransactionMessage(senderId, recieverId, message, TransactionId);
    }

    @Override
    public List<MessageTransactionDto> getAllTransactionMessages(Integer transactionId,Integer userId ,Integer pageNum) {
        Short notSeenState=0;
        Short isSeen=1;
        TransactionInfo transactionInfo=transactionDelegateInterfaceImpl.checkIfTransactionExist(transactionId);
        UserTable user=userDelegateInterfaceImpl.getUserById(userId);
        if(transactionInfo!=null&&user!=null) {//check users exist in transactions or noit
            messageDelegateInterface.updateAllTransactionMessagesSeenState(notSeenState, isSeen, new Date(), transactionInfo, user);
            return messageDelegateInterface.getAllTransactionMessages(transactionId, pageNum);
        }
        return null;
    }

    @Override
    public List<TransactionChatDto> getAllUserTransactionChats(Integer userId,Integer pageNum) {
        List<Integer>userTransactionIdsList=messageDelegateInterface.getUserTransactionIdsList(userId,pageNum);
        List<TransactionChatDto>transactionChatDtosList=null;
       if(userTransactionIdsList!=null) {
           for (Integer transactionId : userTransactionIdsList) {
               if (transactionChatDtosList == null) {
                   transactionChatDtosList = new ArrayList<>();
                   TransactionChatDto transactionChatDto = AppFactory.getTransactionChatDtoInstance();
                   transactionChatDto.setTransactionId(transactionId);
                   transactionChatDto.setTransactionChatMessagesList(messageDelegateInterface.getAllTransactionMessages(transactionId, pageNum));
                   transactionChatDtosList.add(transactionChatDto);
               } else {
                   TransactionChatDto transactionChatDto = AppFactory.getTransactionChatDtoInstance();
                   transactionChatDto.setTransactionId(transactionId);
                   transactionChatDto.setTransactionChatMessagesList(messageDelegateInterface.getAllTransactionMessages(transactionId, pageNum));
                   transactionChatDtosList.add(transactionChatDto);
               }
           }
       }
        return transactionChatDtosList;
    }

}
