/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_services.bussinesslayer;


import api_services.dal.daos.badge.BadgeCustomDAOImpl;
import api_services.dal.daos.badge.BadgeDataInterface;
import entities.Badge;
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
