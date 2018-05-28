/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer;

import com.service_exchange.api_services.dao.user.UserDaoImpl;
import com.service_exchange.api_services.dao.user.UserInterFace;
import com.service_exchange.entities.Challenge;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 *
 * @author Altysh
 */
@Component
public class UserService implements UserInterFace{
    @Autowired
    private UserDaoImpl daoImpl;
    @Override
    public UserTable createUser(UserTable user) {
    return daoImpl.createUser(user);
    }
    public String getSome(){
        if(daoImpl ==null)
        return "hello";
        else return "good";
    }
    @Override
    public UserTable updateUser(UserTable user) {
    return daoImpl.updateUser(user);
    }

    @Override
    public Boolean checkEmailAvalible(Integer email) {
   return daoImpl.checkEmailAvalible(email);
    }

    @Override
    public Page<UserTable> getAllUser(int start) {
      
   return  daoImpl.getAllUser(start);
    }
    public List<UserTable> getAllUser(){
        return daoImpl.getAllUser();
    }

    @Override
    public Page<UserTable> scerchUserByName(String name, int start) {
   return daoImpl.scerchUserByName(name,start);
    }

    @Override
    public Long getDuration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getDurtation(UserTable user, long start, long end) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TransactionInfo> getSuccsfullTransaction(UserTable user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean addChallangeToUser(Integer challangeId,Integer userId) {
   return daoImpl.addChallangeToUser(challangeId, userId);
    }
     @Override
    public Boolean removeChallangeToUser(Integer challangeId,Integer userId) {
   return daoImpl.removeChallangeToUser(challangeId, userId);
    }
    
}
