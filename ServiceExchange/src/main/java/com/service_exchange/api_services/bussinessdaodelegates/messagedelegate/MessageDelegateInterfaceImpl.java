/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.messagedelegate;

import com.service_exchange.api_services.dao.complaint.ComplaintDaoInterface;
import com.service_exchange.api_services.dao.message.MessageInterface;
import com.service_exchange.api_services.dao.message.messagedtos.MessageComplaintDto;
import com.service_exchange.api_services.dao.message.messagedtos.MessagePrivateDto;
import com.service_exchange.api_services.dao.message.messagedtos.MessageTransactionDto;
import com.service_exchange.api_services.dao.transaction.TransactionDaoInterface;
import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.AdminTable;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.Message;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author esraa
 */
@Component
public class MessageDelegateInterfaceImpl implements MessageDelegateInterface{

    @Autowired         
    private MessageInterface messageInterface;
    
    @Autowired 
    private UserDataInterFace userDataInterface;
//    
    @Autowired
    private ComplaintDaoInterface complaintDaoInterfaceImpl;
    
    @Autowired
    private TransactionDaoInterface transactionDaoInterfaceImpl;
    
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
            Message messageTemp=messageInterface.save(message);
           // MessagePrivateDto messagePrivateDto=modelMapper.map(messageTemp, MessagePrivateDto.class);
            MessagePrivateDto messagePrivateDto=AppFactory.mapToDto(messageTemp, MessagePrivateDto.class);
            messagePrivateDto.setSenderId(senderId);
            messagePrivateDto.setReceiverId(recieverId);  
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
    public MessageComplaintDto userSendComplaintMessage(Integer senderId, Integer complaintId, Message message) {
       try{System.err.println("hna");
           Complaint complaint=complaintDaoInterfaceImpl.findById(complaintId).get();
           UserTable sender=userDataInterface.findById(senderId).get();
       if(complaint!=null&&sender!=null&&senderId==complaint.getUserId().getId())
       {System.err.println("hna1");
           message.setSenderId(sender);
           message.setComplaintId(complaint);
           Message messageTemp=messageInterface.save(message);
           MessageComplaintDto messageComplaintDto= AppFactory.mapToDto(messageTemp, MessageComplaintDto.class);
           messageComplaintDto.setComplaintId(complaintId);
           messageComplaintDto.setSenderId(senderId);
           return messageComplaintDto;
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
    public MessageComplaintDto adminSendComplaintMessage(Integer complaintId, Message message) {
         try{
           Complaint complaint=complaintDaoInterfaceImpl.findById(complaintId).get();
      if(complaint!=null)
       {
           message.setComplaintId(complaint);
           message=messageInterface.save(message);
           MessageComplaintDto messageComplaintDto= AppFactory.mapToDto(message, MessageComplaintDto.class);
           messageComplaintDto.setComplaintId(complaintId);
           return messageComplaintDto;
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
    public List<MessageComplaintDto> getAllComplaintMessages(Integer complaintId, Integer pageNum) {
         return messageInterface.getAllComplaintMessages(complaintId, pageNum);
    }

    @Override
    public MessageTransactionDto sendTransactionMessage(Integer senderId, Integer recieverId, Message message, Integer transactionId) {
   
        try{
        TransactionInfo transactionInfo=transactionDaoInterfaceImpl.findById(transactionId).get();
        UserTable sender=userDataInterface.findById(senderId).get();
        UserTable receiver=userDataInterface.findById(recieverId).get();
        if(sender!=null &&receiver!=null&&transactionInfo!=null
                &&checkUsersInTransaction(senderId,recieverId,transactionInfo)==true)
        {
            message.setSenderId(sender);
            message.setReceiverId(receiver);
            message.setTransactionId(transactionInfo);
            message=messageInterface.save(message);
            MessageTransactionDto messageTransactionDto=AppFactory.mapToDto(message, MessageTransactionDto.class);
            messageTransactionDto.setSenderId(senderId);
            messageTransactionDto.setReceiverId(recieverId);
            messageTransactionDto.setTransactionId(transactionId);
            return messageTransactionDto;
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
    public List<MessageTransactionDto> getAllTransactionMessages(Integer transactionId, Integer pageNum) {
        return messageInterface.getAllTransactionMessages(transactionId, pageNum);
    
    }
    
    private boolean checkUsersInTransaction(Integer senderId,Integer receiverId,TransactionInfo transactionInfo)
    {
        if((senderId!=receiverId)&&(transactionInfo.getStartedBy().getId()==senderId||transactionInfo.getStartedBy().getId()==receiverId)
                && (transactionInfo.getServiceId().getMadeBy().getId()==senderId||transactionInfo.getServiceId().getMadeBy().getId()==receiverId))
        {
            return true;
        }
        return false;
    }
}
