/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer.messagebussiness;

import com.service_exchange.api_services.bussinessdaodelegates.complaintdelegate.ComplaintDelegateInterface;
import com.service_exchange.api_services.bussinessdaodelegates.messagedelegate.MessageDelegateInterface;
import com.service_exchange.api_services.bussinessdaodelegates.service.ServiceGettable;
import com.service_exchange.api_services.bussinessdaodelegates.transaction.TransactionDelegateInterface;
import com.service_exchange.api_services.bussinessdaodelegates.user.UserDelegateInterface;
import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.MessagePrivateDto;
import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private ServiceGettable serviceGettableImpl;
    @Override
    public MessagePrivateDto sendPrivateMessage(Integer senderId, Integer recieverId, Message message) {
      
        return messageDelegateInterface.sendPrivateMessage(senderId, recieverId, message);
    }

    @Override
    public List<MessagePrivateDto> getAllPrivateChatMessages(Integer user1Id, Integer user2Id, Integer pageNum) {
        return messageDelegateInterface.getAllPrivateChatMessages(user1Id, user2Id, pageNum);
    }

    @Override
    public MessageGeneralDto userSendComplaintMessage(Integer senderId, Integer ComplaintId,Integer transactionId, Message message) {
        return messageDelegateInterface.userSendComplaintMessage(senderId, ComplaintId, transactionId, message);
    }

    @Override
    public MessageGeneralDto adminSendComplaintMessage( Integer ComplaintId,Integer transactionId, Message message) {
       return messageDelegateInterface.adminSendComplaintMessage(ComplaintId, transactionId, message);
    }

    @Override
    public List<MessageGeneralDto> getAllComplaintMessages(Integer complaintId,Integer senderId, Integer pageNum) {
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
    public MessageGeneralDto sendTransactionMessage(Integer senderId, Integer recieverId, Message message, Integer TransactionId) {
            return messageDelegateInterface.sendTransactionMessage(senderId, recieverId, message, TransactionId);
    }

    @Override
    public List<MessageGeneralDto> getAllTransactionMessages(Integer transactionId,Integer userId ,Integer pageNum) {
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
       try {
           List<Integer> userTransactionIdsList = messageDelegateInterface.getUserTransactionIdsList(userId, pageNum);
           List<TransactionChatDto> transactionChatDtosList = new ArrayList<>();
           if (userTransactionIdsList != null) {
               for (Integer transactionId : userTransactionIdsList) {
                   if (transactionChatDtosList == null) {
                       transactionChatDtosList = new ArrayList<>();
                   }
                       TransactionChatDto transactionChatDto = AppFactory.getTransactionChatDtoInstance();
                       transactionChatDto.setTransactionId(transactionId);
                       transactionChatDto.setTransactionChatMessagesList(messageDelegateInterface.getAllTransactionMessages(transactionId, pageNum));
                       TransactionInfo transactionInfo=transactionDelegateInterfaceImpl.checkIfTransactionExist(transactionId);
                       UserTable user=transactionInfo.getStartedBy();
                   if (user != null && !Objects.equals(user.getId(), userId))
                       {
                           //hoa da l user l tany
                           transactionChatDto.setUserId(user.getId());
                           transactionChatDto.setUserName(user.getName());
                           transactionChatDto.setUserImage(user.getImage());
                           transactionChatDto.setUserStatus(user.getStatus());
                           Service service=serviceGettableImpl.getService(transactionInfo.getServiceId().getId());
                           transactionChatDto.setServiceId(service.getId());
                           transactionChatDto.setServiceName(service.getName());
                           transactionChatDtosList.add(transactionChatDto);
                       } else {
                       if (transactionInfo.getServiceId() != null)
                           user = transactionInfo.getServiceId().getMadeBy();
                           if(user!=null)
                           {   transactionChatDto.setUserId(user.getId());
                               transactionChatDto.setUserName(user.getName());
                               transactionChatDto.setUserImage(user.getImage());
                               transactionChatDto.setUserStatus(user.getStatus());
                               Service service=serviceGettableImpl.getService(transactionInfo.getServiceId().getId());
                               transactionChatDto.setServiceId(service.getId());
                               transactionChatDto.setServiceName(service.getName());
                               transactionChatDtosList.add(transactionChatDto);
                           }
                       }


               }
           }
           return transactionChatDtosList;
       }catch (Exception e)
       {
           e.printStackTrace();
           return  null;
       }
    }

    @Override
    public List<MessageGeneralDto> selectAllUserUnreadedMessages(Integer userId) {
        return  messageDelegateInterface.selectAllUserUnreadedMessages(userId);
    }

    @Override
    public List<MessageGeneralDto> selectAllUserUnreadedMessages(Integer userId,Integer pageNumber) {
        return  messageDelegateInterface.selectAllUserUnreadedMessages(userId,pageNumber);
    }

}
