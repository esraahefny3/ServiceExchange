/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer;


import com.service_exchange.api_services.dao.badge.BadgeCustomDAOImpl;
import com.service_exchange.api_services.dao.badge.BadgeDataInterface;
import com.service_exchange.entities.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author esraa
 */
@Component
public class BadgeService {
    
    @Autowired
    private BadgeCustomDAOImpl badgeCustomDAOImpl;
    
    @Autowired
    private BadgeDataInterface badgeDataInterface;
    
    public void  createBadge(Badge badge)
    { 
        badgeDataInterface.save(badge);
        
    }
}
