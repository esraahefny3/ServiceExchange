/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.messagedelegate;

import com.service_exchange.api_services.dao.complaint.ComplaintDaoInterface;
import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.MessagePrivateDto;
import com.service_exchange.api_services.dao.message.MessageInterface;
import com.service_exchange.api_services.dao.transaction.TransactionCustomInterface;
import com.service_exchange.api_services.dao.transaction.TransactionDaoInterface;
import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.Message;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author esraa
 */
@Component
public class MessageDelegateInterfaceImpl implements MessageDelegateInterface{

    private int pageSize=20;
    @Autowired         
    private MessageInterface messageInterface;
    
    @Autowired 
    private UserDataInterFace userDataInterface;
//    
    @Autowired
    private ComplaintDaoInterface complaintDaoInterfaceImpl;
    
    @Autowired
    private TransactionDaoInterface transactionDaoInterfaceImpl;


    @Autowired
    private TransactionCustomInterface transactionCustomInterfaceImpl;
//    public Message returnMessage(Integer messageId){
//        
//        return messageInterface.findById(messageId).get();
//    }
    @Override
    public MessagePrivateDto sendPrivateMessage(Integer senderId, Integer recieverId, Message message) {
   
     try{
        UserTable sender=userDataInterface.findById(senderId).get();
        UserTable reciever=userDataInterface.findById(recieverId).get();
       
        if(sender!=null&&reciever!=null)
        {
            message.setSenderId(sender);
            message.setReceiverId(reciever);
            message.setDate(new Date());
            Message messageTemp=messageInterface.save(message);
           // MessagePrivateDto messagePrivateDto=modelMapper.map(messageTemp, MessagePrivateDto.class);
            MessagePrivateDto messagePrivateDto=AppFactory.mapToDto(messageTemp, MessagePrivateDto.class);
            messagePrivateDto.setSenderId(senderId);
            messagePrivateDto.setReceiverId(recieverId);
            messagePrivateDto.setDate(messageTemp.getDate().getTime());
            return messagePrivateDto;
        }
        return null;
      }catch(Exception e)
      {
          e.printStackTrace();
          return null;
      }
      
    }

    @Override
    public List<MessagePrivateDto> getAllPrivateChatMessages(Integer user1Id, Integer user2Id, Integer pageNum) {
   
        try{
       return messageInterface.findAllPrivateChatMessages(user1Id, user2Id, pageNum);
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MessageGeneralDto userSendComplaintMessage(Integer senderId, Integer complaintId, Integer transactionId, Message message) {
       try{
           Complaint complaint=complaintDaoInterfaceImpl.findById(complaintId).get();
           UserTable sender=userDataInterface.findById(senderId).get();
           Optional<TransactionInfo> transactionInfoOptional=transactionDaoInterfaceImpl.findById(transactionId);
       if(transactionInfoOptional.isPresent()&& complaint!=null&&sender!=null&&senderId==complaint.getUserId().getId()&&complaint.getTransactionId().getId()==transactionId)
       {
           message.setTransactionId(transactionInfoOptional.get());
           message.setSenderId(sender);
           message.setComplaintId(complaint);
           message.setDate(new Date());
           Short isSeen=0;//not seen yet
           message.setIsSeen(isSeen);
           Message messageTemp=messageInterface.save(message);
           MessageGeneralDto messageGeneralDto= AppFactory.mapToDto(messageTemp, MessageGeneralDto.class);
           messageGeneralDto.setComplaintId(complaintId);
           messageGeneralDto.setTransactionId(transactionId);
           messageGeneralDto.setSenderId(senderId);
           messageGeneralDto.setDate(messageTemp.getDate().getTime());
           return messageGeneralDto;
       }
         return null;
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public MessageGeneralDto adminSendComplaintMessage(Integer complaintId,Integer transactionId, Message message) {
         try{
           Complaint complaint=complaintDaoInterfaceImpl.findById(complaintId).get();
             Optional<TransactionInfo> transactionInfoOptional=transactionDaoInterfaceImpl.findById(transactionId);
             if(transactionInfoOptional.isPresent()&&complaint!=null&&complaint.getTransactionId().getId()==transactionId)
       {
           message.setTransactionId(transactionInfoOptional.get());
           message.setComplaintId(complaint);
           message.setReceiverId(complaint.getUserId());
           message.setDate(new Date());
           Short isSeen=0;//not seen yet
           message.setIsSeen(isSeen);
           message=messageInterface.save(message);
           MessageGeneralDto messageGeneralDto= AppFactory.mapToDto(message, MessageGeneralDto.class);
           messageGeneralDto.setComplaintId(complaintId);
           messageGeneralDto.setDate(message.getDate().getTime());
           messageGeneralDto.setTransactionId(transactionId);
           return messageGeneralDto;
       }
         return null;
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public List<MessageGeneralDto> getAllComplaintMessages(Integer complaintId, Integer pageNum) {
         return messageInterface.getAllComplaintMessages(complaintId, pageNum);
    }

    @Override
    public MessageGeneralDto sendTransactionMessage(Integer senderId, Integer recieverId, Message message, Integer transactionId) {


        try {
            Optional<TransactionInfo> transactionInfoOptional = transactionDaoInterfaceImpl.findById(transactionId);
            if (transactionInfoOptional.isPresent()) { System.out.println("heree0");
                TransactionInfo transactionInfo = transactionInfoOptional.get();
                Optional<UserTable> senderOptional = userDataInterface.findById(senderId);
                Optional<UserTable> receiverOptional = userDataInterface.findById(recieverId);
                if (senderOptional.isPresent() == true && receiverOptional.isPresent() == true) {
                    UserTable sender=senderOptional.get();
                    UserTable receiver=receiverOptional.get();
                    if (sender != null && receiver != null && transactionInfo != null
                            && checkUsersInTransaction(senderId, recieverId, transactionInfo) == true) {
                        message.setSenderId(sender);
                        message.setReceiverId(receiver);
                        message.setTransactionId(transactionInfo);
                        message.setDate(new Date());
                        Short isSeen = 0;//not seen yet
                        message.setIsSeen(isSeen);
                        message = messageInterface.save(message);
                        MessageGeneralDto messageGeneralDto = AppFactory.mapToDto(message, MessageGeneralDto.class);
                        messageGeneralDto.setSenderId(senderId);
                        messageGeneralDto.setReceiverId(recieverId);
                        messageGeneralDto.setTransactionId(transactionId);
                        messageGeneralDto.setDate(message.getDate().getTime());
                       // messageGeneralDto.setReadDate(message.getSeenDate().getTime());
                        return messageGeneralDto;
                    }
                }
            }
                return null;
            }
        catch(Exception e)
            {
                e.printStackTrace();
                return null;
            }

    }

    @Override
    public List<MessageGeneralDto> getAllTransactionMessages(Integer transactionId, Integer pageNum) {

        return messageInterface.getAllTransactionMessages(transactionId, pageNum);
    
    }

    @Override
    public List<Integer> getUserTransactionIdsList(Integer userId, Integer pageNum) {
        List<Integer> transactionIdsList = null;
        Optional<UserTable> userTableOptional = userDataInterface.findById(userId);
        if (userTableOptional.isPresent() == true) {
            transactionIdsList = transactionCustomInterfaceImpl.getAllUserTransactions(userId, pageNum);
        }
            return transactionIdsList;

    }

    @Override
    public int updateAllTransactionMessagesSeenState(Short notSeenState, Short isSeen, Date seenDate, TransactionInfo transactionId, UserTable senderId) {
       return messageInterface.updateAllTransactionMessagesSeenState(notSeenState,isSeen,seenDate,transactionId,senderId);
    }

    @Override
    public int updateAllComplaintMessagesSeenState(Short notSeenState, Short isSeen, Date seenDate, Complaint complaintId, UserTable senderId) {
        return messageInterface.updateAllComplaintMessagesSeenState(notSeenState,isSeen,seenDate,complaintId,senderId);

    }

    @Override
    public boolean checkUsersInTransaction(Integer senderId, Integer receiverId, TransactionInfo transactionInfo)
    {
        if((senderId!=receiverId)&&(transactionInfo.getStartedBy().getId()==senderId||transactionInfo.getStartedBy().getId()==receiverId)
                && (transactionInfo.getServiceId().getMadeBy().getId()==senderId||transactionInfo.getServiceId().getMadeBy().getId()==receiverId))
        {
            return true;
        }
        return false;
    }

    @Override
    public List<MessageGeneralDto> selectAllUserUnreadedMessages(Integer userId) {
      try {
          Optional<UserTable> userTableOptional=userDataInterface.findById(userId);
          if (userTableOptional.isPresent()) {

              List<Message> messageList = messageInterface.selectAllUserUnreadedMessages(userTableOptional.get());
              List<MessageGeneralDto> messageGeneralDtoList = new ArrayList<>();
              messageList.forEach(message -> messageGeneralDtoList.add(AppFactory.mapToDto(message, MessageGeneralDto.class)));
              return messageGeneralDtoList;
          }
          else {
              return null;
          }
      }
      catch (Exception e)
      {
          e.printStackTrace();
          return null;
      }
    }


    @Override
    public List<MessageGeneralDto> selectAllUserUnreadedMessages(Integer userId,Integer pageNumber) {
        try {
            Optional<UserTable> userTableOptional = userDataInterface.findById(userId);
            if (userTableOptional.isPresent()) {

                List<Message> messageList = messageInterface.selectAllUserUnreadedMessages(userTableOptional.get(), PageRequest.of(pageNumber, pageSize));
                List<MessageGeneralDto> messageGeneralDtoList = new ArrayList<>();
                messageList.forEach(message -> messageGeneralDtoList.add(AppFactory.mapToDto(message, MessageGeneralDto.class)));
                return messageGeneralDtoList;
            }
            else {
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
