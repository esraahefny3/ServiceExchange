/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.restcontrollers;

import com.service_exchange.api_services.bussinesslayer.BadgeService;
import com.service_exchange.api_services.dao.dto.BadgeDto;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.AdminTable;
import com.service_exchange.entities.Badge;
import com.service_exchange.utal.PageToListConverter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/badge")
public class BadgeController {
    
    @Autowired
    private BadgeService badgeService;
    
    private Integer pageSize=20;
    
    @RequestMapping(value = "/getBadge/{id}", method = RequestMethod.GET)
    public BadgeDto getBadge(@PathVariable("id") Integer id) {

               Badge badgeTemp= badgeService.getBadge(id);
               BadgeDto badgeDto=AppFactory.mapToDto(badgeTemp,BadgeDto.class);
               badgeDto.setAddedByAdminEmail(badgeTemp.getAddedBy().getEmail());
               return badgeDto;
    }
    
    
    @RequestMapping(value = "/createBadge", method = RequestMethod.POST)
    public BadgeDto createBadge(@RequestBody BadgeDto badgeDto) {

        if(badgeDto !=null)
        {
            Badge badge=AppFactory.mapToEntity(badgeDto,Badge.class);
            AdminTable admin=AppFactory.getAdminTableInstance();
            admin.setEmail(badgeDto.getAddedByAdminEmail());
            badge.setAddedBy(admin);
           Badge badgeTemp= badgeService.createBadge(badge);
            BadgeDto badgeDto2=AppFactory.mapToDto(badgeTemp,BadgeDto.class);
            badgeDto2.setAddedByAdminEmail(badgeTemp.getAddedBy().getEmail());
            return badgeDto2;
        }
        else
        {
            return null;
        }
    }
    
    @RequestMapping(value = "/deleteBadge", method = RequestMethod.POST)
    public boolean deleteBadge(@RequestBody BadgeDto badgeDto) {
        if(badgeDto !=null)
        {
           if(badgeDto.getId()!=null)
           {
               Badge badge=AppFactory.mapToEntity(badgeDto,Badge.class);
               AdminTable admin=AppFactory.getAdminTableInstance();
               admin.setEmail(badgeDto.getAddedByAdminEmail());
               badge.setAddedBy(admin);

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
    public BadgeDto updateBadge(@RequestBody BadgeDto badgeDto) {
        
        if(badgeDto!=null)
        { 
            if(badgeDto.getId()!=null)
            {
                Badge badge=AppFactory.mapToEntity(badgeDto,Badge.class);
                AdminTable admin=AppFactory.getAdminTableInstance();
                admin.setEmail(badgeDto.getAddedByAdminEmail());
                badge.setAddedBy(admin);

                 Badge badge2=badgeService.updateBadge(badge);
                BadgeDto badgeDto2=AppFactory.mapToDto(badge2,BadgeDto.class);
                badgeDto2.setAddedByAdminEmail(badge2.getAddedBy().getEmail());
                return badgeDto2;
                
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
    public List<BadgeDto> getAllBadges(@PathVariable("pageNum") Integer pageNum) {
       List<Badge>badgeList= PageToListConverter.convertList(badgeService.getAllBadges(PageRequest.of((pageNum!=null)?pageNum:0,pageSize)));
       List<BadgeDto>badgeDtoList=new ArrayList<>();
     //  badgeList.forEach(badge -> badgeDtoList.add(AppFactory.mapToDto(badge,BadgeDto.class)));
        for (Badge badge:badgeList)
        {
            BadgeDto badgeDto=AppFactory.mapToDto(badge,BadgeDto.class);
            badgeDto.setAddedByAdminEmail(badge.getAddedBy().getEmail());
            badgeDtoList.add(badgeDto);
        }
       
       
       return badgeDtoList;
       // return badgeService.getAllBadges(PageRequest.of((pageNum!=null)?pageNum:0,pageSize));
    }
    
     @RequestMapping(value = "/getAllBadges", method = RequestMethod.GET)
    public Iterable<BadgeDto> getAllBadges() {
       List<Badge>badgeList=  PageToListConverter.convertIterableToList(badgeService.getAllBadges());
         List<BadgeDto>badgeDtoList=new ArrayList<>();
         for (Badge badge:badgeList)
         {
             BadgeDto badgeDto=AppFactory.mapToDto(badge,BadgeDto.class);
             badgeDto.setAddedByAdminEmail(badge.getAddedBy().getEmail());
             badgeDtoList.add(badgeDto);
         }
         return badgeDtoList;


     }

}
