/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer;

import com.service_exchange.api_services.dao.user.UserDaoImpl;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author Altysh
 */
@Component
public class UserService{
    @Autowired
    private UserDaoImpl daoImpl;

    public UserTable createUser(UserTable user) {
    return daoImpl.createUser(user);
    }
    public String getSome(){
        if(daoImpl ==null)
        return "hello";
        else return "good";
    }

    public UserTable updateUser(UserTable user) {
    return daoImpl.updateUser(user);
    }

  
    public Boolean checkEmailAvalible(Integer email) {
   return daoImpl.checkEmailAvalible(email);
    }


    public Page<UserTable> getAllUser(int start) {
      
   return  daoImpl.getAllUser(start);
    }
    public List<UserTable> getAllUser(){
        return daoImpl.getAllUser();
    }

    
    public Page<UserTable> scerchUserByName(String name, int start) {
   return daoImpl.scerchUserByName(name,start);
    }



  
    
}
