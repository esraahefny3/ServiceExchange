/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.userdelegates.userbadgedelegate;

import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserTable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author Altysh
 */

public interface UserBadgesSelegateInterface {
    public UserTable checkIfUserExist(Integer userId);
    public List<Badge> getAllUserBadges(UserTable user);
   public List<Badge> getAllUserBadges(UserTable user, Pageable page);
    public boolean assignBadgeToUser(UserTable user,Badge Badge);
    public Long getUserWorkingHourse(Integer userId);
   
    //isUserDeserveBadge
    
    
}
