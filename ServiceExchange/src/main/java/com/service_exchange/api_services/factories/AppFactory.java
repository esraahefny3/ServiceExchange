/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.factories;

import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.service_exchange.entities.AdminTable;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.UserTable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author esraa
 */
@Component
public class AppFactory {
    
   private static AppFactory appFactory;
   @Autowired
    private  ModelMapper modelMapper;

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public <T, S> List<T> mapList(List<S> list, Type type) {
        return modelMapper.map(list, type);
    }

    @PostConstruct
    public void registerInstance() {
        this.appFactory = this;
    }
   public static <T,M> M mapToDto(T t,Class<M> m)
   {
       return (M)appFactory.modelMapper.map(t, m);
   }
    public static <T,M> M mapToEntity(T t,Class<M> m)
    {
        return (M)appFactory.modelMapper.map(t, m);
    }
   
   public static Complaint getComplaintInstance()
   {
       return new Complaint();
   }
   
   public static UserTable getUserTableInstance()
   {
       return new UserTable();
   }
   
   public static AdminTable getAdminTableInstance()
   {
       return new AdminTable();
   }

   public static TransactionChatDto getTransactionChatDtoInstance()
    {
        return new TransactionChatDto();
    }
}
