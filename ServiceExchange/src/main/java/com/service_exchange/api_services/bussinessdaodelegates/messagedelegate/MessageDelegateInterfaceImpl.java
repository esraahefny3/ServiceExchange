/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.messagedelegate;

import com.service_exchange.api_services.dao.message.MessageInterface;
import com.service_exchange.api_services.dao.message.messagedtos.MessagePrivateDto;
import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.api_services.factories.AppFactory;
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
    
    @Autowired
    private ModelMapper modelMapper;
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
