/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userbadge;

import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserBadge;
import com.service_exchange.entities.UserTable;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Altysh
 */
public interface UserBadgesInterface {
    
   public List<Badge> getAllUserBadge(Integer userId, Pageable page);
   public boolean assignBadgeToUser(UserTable user,UserBadge userBadge);
   
    //isUserDeserveBadge
    
    
}
