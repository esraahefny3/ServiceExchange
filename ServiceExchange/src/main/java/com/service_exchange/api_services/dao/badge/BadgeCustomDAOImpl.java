/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.badge;

import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserTable;
import org.springframework.stereotype.Component;

/**
 *
 * @author esraa
 */

@Component
public class BadgeCustomDAOImpl implements BadgeCustomInterface{

    @Override
    public boolean isUserDeserveBadge(UserTable user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean assignBadgeToUser(UserTable user, Badge badge) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
  
}
