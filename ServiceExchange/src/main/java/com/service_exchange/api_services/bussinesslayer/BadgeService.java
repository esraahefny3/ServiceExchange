/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer;

import com.service_exchange.api_services.dao.badge.BadgeCustomDAOImpl;
import com.service_exchange.api_services.dao.badge.BadgeDataInterface;
import com.service_exchange.entities.Badge;
import java.util.Optional;
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
    
    public Badge getBadge(int id)
    { 
        Optional<Badge>retrievedBadge= badgeDataInterface.findById(id);
        if (retrievedBadge.isPresent())
        {
            return retrievedBadge.get();
        }
        else
        {
            return null;
        }
    }
    
    public Badge  createBadge(Badge badge)
    { 
        return badgeDataInterface.save(badge); 
    }
    
    public boolean deleteBadge(Badge badge)
    {
        try{
            badgeDataInterface.delete(badge);
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    
    public Badge updateBadge(Badge badge)
    {
        if(badgeDataInterface.existsById(badge.getId()))
        {
            return badgeDataInterface.save(badge);
        }
        else
        {
           return null;
        }
    }
    
     
    public Badge getAllBadge()
    {
       return (Badge) badgeDataInterface.findAll(); 
    }
}
