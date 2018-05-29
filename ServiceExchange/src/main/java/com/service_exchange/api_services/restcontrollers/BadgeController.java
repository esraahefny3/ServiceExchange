/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.restcontrollers;

import com.service_exchange.api_services.bussinesslayer.BadgeService;
import com.service_exchange.entities.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author esraa
 */
@RestController
public class BadgeController {
    
    @Autowired
    private BadgeService badgeService;
    
    private int pageSize=20;
    
    @RequestMapping(value = "/getBadge/{id}", method = RequestMethod.GET)
    public Badge getBadge(@PathVariable("id") int id) {

               return badgeService.getBadge(id);
           
    }
    
    
    @RequestMapping(value = "/createBadge", method = RequestMethod.POST)
    public Badge createBadge(@RequestBody Badge badge) {

        if(badge !=null)
        {
           return badgeService.createBadge(badge);
        }
        else
        {
            return null;
        }
    }
    
    @RequestMapping(value = "/deleteBadge", method = RequestMethod.POST)
    public boolean deleteBadge(@RequestBody Badge badge) {
       if(badge!=null)
       {
           if(badge.getId()!=null)
           {
               return badgeService.deleteBadge(badge);
           }
           else
           {
               return false;
           }
       }
       else
       {
           return false;
       }
             
       
    }
    
    @RequestMapping(value = "/updateBadge", method = RequestMethod.POST)
    public Badge updateBadge(@RequestBody Badge badge) {
        
        if(badge!=null)
        { 
            if(badge.getId()!=null)
            {
                System.err.println(badge.getId());
                return badgeService.updateBadge(badge);
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    @RequestMapping(value = "/getAllBadges/{pageNum}", method = RequestMethod.GET)
    public Badge getAllBadges(@PathVariable("num") int pageNum) {
       return badgeService.getAllBadge(PageRequest.of(pageNum,pageSize));
    }
    
    @RequestMapping(value = "/hii", method = RequestMethod.GET)
    public String sayHii() {
        return "Hii";
    }
}
