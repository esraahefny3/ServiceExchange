/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer;

import com.service_exchange.api_services.bussinessdaodelegates.userdelegates.userbadgedelegate.UserBadgesSelegateInterface;
import com.service_exchange.api_services.dao.dto.EdcationDTO;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.service_exchange.api_services.dao.user.UserDaoImpl;
import com.service_exchange.api_services.delegte.UserDataDelete;
import com.service_exchange.api_services.delegte.UserDataGet;
import com.service_exchange.api_services.delegte.UserDataSet;
import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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


    //--user badge
    //mubarak//
    @Autowired
    private UserDataGet userDataGet;
    public List<UserTable> getAllUser(){
        return daoImpl.getAllUser();
    }


    public Page<UserTable> scerchUserByName(String name, int start) {
   return daoImpl.scerchUserByName(name,start);
    }


    //---user badge
    // @Autowired
    private UserBadgesSelegateInterface userBadgesInterface;

    private int pageSize=20;

   public List<Badge> getAllUserBadges(Integer userId, Integer pageNum){
       if(userId!=null&& pageNum!=null)
        {
            UserTable userNew=userBadgesInterface.checkIfUserExist(userId);
            if(userNew!=null)
            {
                return userBadgesInterface.getAllUserBadges(userNew, PageRequest.of((pageNum!=null)?pageNum:0,pageSize));
            }
        }
         return null;
   }
   public boolean assignBadgeToUser(UserTable user,Badge badge){
       if(user!=null &&user.getId()!=null&&badge!=null&&badge.getId()!=null)
       {
           return userBadgesInterface.assignBadgeToUser(user, badge);
       }
       else
       {
           return false;
       }
   }

    public Page<UserTable> getAllUser(int start) {

        return daoImpl.getAllUser(start);
    }

    public List<Badge> getAllUserBadges(Integer userId) {
        if (userId != null) {
            UserTable userNew = userBadgesInterface.checkIfUserExist(userId);
            if (userNew != null) {
                return userBadgesInterface.getAllUserBadges(userNew);
            }
        }
        return null;

    }

    //mubarak//
    @Autowired
    private UserDataSet userDataSet;
    @Autowired
    private UserDataDelete userDataDelete;
    public UserDTO logInORSignUp(UserDTO userDTO) {
        return userDataGet.loginOrSignUp(userDTO);
    }

    public List<SkillDTO> getUserSkill(Integer userId) {
        return userDataGet.getUserSkill(userId);
    }

    public List<EdcationDTO> getUserEdcation(Integer userId) {
        return userDataGet.getUserEdcation(userId);
    }

    public List<ServiceDTO> getUserService(Integer userId) {
        return userDataGet.getUserServices(userId);
    }


    public Boolean addEmail(String email, Integer userId) {
        return userDataSet.addEmailToUser(email, userId);
    }

    public Boolean addSkill(SkillDTO skillDTO, Integer userId) {
        return userDataSet.addSkillToUser(skillDTO, userId);
    }

    public Boolean addTelephone(String telephone, Integer userId) {
        return userDataSet.addTelephonToUser(telephone, userId);
    }

    public Boolean removeTelephone(String telephone, Integer userId) {
        return userDataDelete.removeTelePhoneFormUser(telephone, userId);
    }

    public Boolean removeEmail(String email, Integer userId) {
        return userDataDelete.removeEmailFormUser(email, userId);
    }

    public Boolean removeSkill(Integer skillId, Integer userId) {
        return userDataDelete.removeSkillFormUser(skillId, userId);
    }

    public Boolean removeService(Integer service, Integer userId, Boolean forced) {
        return userDataDelete.removeServiceToUser(userId, service, forced);
    }


    //mubarak//


    
}
