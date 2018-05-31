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
//    @Autowired
//    private ComplaintDaoInterface complaintDaoInterfaceImpl;
//    
//    @Autowired
//    private TransactionDaoInterface transactionDaoInterfaceImpl;
    
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
       // Complaint complaint=
//       Complaint complaint=complaintDaoInterfaceImpl.findById(complaintId).get();
//       message.setComplaintId(complaint);
//       
//       UserTable sender=AppFactory.getUserTableInstance();
//       sender.setId(senderId);
//       message.setSenderId(sender);
//       
//       AdminTable receiver=AppFactory.getAdminTableInstance();
//       receiver.setEmail(complaint.getReviewedBy().getEmail());
//       message.setr
       
       return AppFactory.mapToDto(message, MessageComplaintDto.class);
    }

    @Override
    public MessageComplaintDto adminSendComplaintMessage(Integer adminId, Integer ComplaintId, Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //lsaaaa lma a3ml l complaint dao
    
    }

    @Override
    public List<MessageComplaintDto> getAllComplaintMessages(Integer complaintId, Integer pageNum) {
     return messageInterface.getAllComplaintMessages(complaintId, pageNum);
    }

    @Override
    public MessageTransactionDto sendTransactionMessage(Integer senderId, Integer recieverId, Message message, Integer TransactionId) {
   
     //lsaaaa lma a3ml l complaint dao
    return null;
    }

    @Override
    public List<MessageTransactionDto> getAllTransactionMessages(Integer transactionId, Integer pageNum) {
        return messageInterface.getAllTransactionMessages(transactionId, pageNum);
    
    }
    
}
