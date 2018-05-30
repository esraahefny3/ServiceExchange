/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer;

import com.service_exchange.api_services.bussinessdaodelegates.badgedelegate.BadgeDelegateInterface;
import com.service_exchange.api_services.dao.badge.BadgeCustomImpl;
import com.service_exchange.api_services.dao.badge.BadgeDataInterface;
import com.service_exchange.entities.Badge;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 *
 * @author esraa
 */
@Component
public class BadgeService {
    
    @Autowired
    private BadgeDelegateInterface badgeDelegateInterface;
    

    
    public Badge getBadge(Integer id)
    { 
       if(id!=null)
       {
           return badgeDelegateInterface.getBadge(id);
       }
       else
       {
           return null;
       }
    }
    
    public Badge  createBadge(Badge badge)
    { 
        if(badge!=null&&badge.getId()!=null)
        {
            if(badgeDelegateInterface.checkIfBadgeExist(badge)==false)
            {
                return badgeDelegateInterface.createBadge(badge);
            }
            else
            {
                return null;
            }
        }
       else
       {
           return badgeDelegateInterface.createBadge(badge);
       }
    }
    
    public boolean deleteBadge(Badge badge)
    {
        if(badge!=null&&badge.getId()!=null)
        {
            return badgeDelegateInterface.deleteBadge(badge);
        }
        else
        {
            return false;
        }
    }
    
    public Badge updateBadge(Badge badge)
    {
       if(badge!=null&&badge.getId()!=null)
        {
        return badgeDelegateInterface.updateBadge(badge);
        }
       else
       {
           return null;
       }
    }
    
     
    public Page<Badge> getAllBadges(Pageable page)
    {
        if(page!=null)
        {
            return  badgeDelegateInterface.getAllBadges(page);
        }
        else
        {
            return null;
        }
                
    }
    public Iterable<Badge> getAllBadges()
    {
         return  badgeDelegateInterface.getAllBadges();
    }
}
