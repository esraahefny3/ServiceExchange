/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.badgedelegate;

import com.service_exchange.api_services.dao.badge.BadgeCustomImpl;
import com.service_exchange.api_services.dao.badge.BadgeCustomInterface;
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
public class BadgeDelegateImpl implements BadgeDelegateInterface{
     
     @Autowired
    private BadgeDataInterface badgeDataInterface;

    
    @Override
    public Badge getBadge(Integer id)

    {
        try {

            Optional<Badge> badgeOptional = badgeDataInterface.findById(id.intValue());
            if (badgeOptional.isPresent() == true) {
                return badgeOptional.get();
            } else {
                return null;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Badge  createBadge(Badge badge)
    {
        try {
            return badgeDataInterface.save(badge);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean deleteBadge(Badge badge)
    {
        try{
          badgeDataInterface.delete(badge);
            return true;
        }catch(Exception e)
        {
            return false;//badge not exist
        }
    }
    
    @Override
    public Badge updateBadge(Badge badge)
    {
        try {
            return badgeDataInterface.save(badge);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
       
    }
   
    @Override
    public Page<Badge> getAllBadges(Pageable page)
    {
        try {
            return badgeDataInterface.findAll(page);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Iterable<Badge> getAllBadges() {
        try {
            return badgeDataInterface.findAll();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkIfBadgeExist(Badge badge) {

        try {

            Optional<Badge> retrievedBadge = badgeDataInterface.findById(badge.getId().intValue());
            if (retrievedBadge.isPresent()) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    
    }
}
