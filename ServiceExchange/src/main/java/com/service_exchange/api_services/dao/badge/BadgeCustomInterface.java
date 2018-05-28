/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.badge;

import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserTable;


/**
 *
 * @author esraa
 */
public interface BadgeCustomInterface {
   public boolean isUserDeserveBadge(UserTable user);
   public boolean assignBadgeToUser(UserTable user,Badge badge);
}
