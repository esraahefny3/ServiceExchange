/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user;

import com.service_exchange.entities.Challenge;
import com.service_exchange.entities.Transaction;
import com.service_exchange.entities.User;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author Altysh
 */
public interface UserInterFace  {
    User createUser(User user);
    User updateUser(User user);
    Boolean checkEmailAvalible(Integer email);
     Page<User> getAllUser(int start);
    Page<User> scerchUserByName(String name,int start);
    Long getDuration();
    Long getDurtation(User user,long start,long end);
    List<Transaction> getSuccsfullTransaction(User user); 
    Boolean addChallangeToUser(Integer chId,Integer userId);
   Boolean removeChallangeToUser(Integer chId, Integer userId) ;
    
}
