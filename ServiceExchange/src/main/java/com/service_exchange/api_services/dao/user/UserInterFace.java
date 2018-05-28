/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user;

import com.service_exchange.entities.Challenge;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author Altysh
 */
public interface UserInterFace  {
    UserTable createUser(UserTable user);
    UserTable updateUser(UserTable user);
    Boolean checkEmailAvalible(Integer email);
     Page<UserTable> getAllUser(int start);
    Page<UserTable> scerchUserByName(String name,int start);
    Long getDuration();
    Long getDurtation(UserTable user,long start,long end);
    List<TransactionInfo> getSuccsfullTransaction(UserTable user); 
    Boolean addChallangeToUser(Integer chId,Integer userId);
   Boolean removeChallangeToUser(Integer chId, Integer userId) ;
    
}
