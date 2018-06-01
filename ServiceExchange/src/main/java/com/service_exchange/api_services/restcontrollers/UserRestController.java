/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.restcontrollers;

import com.service_exchange.api_services.bussinesslayer.UserService;
import com.service_exchange.api_services.dao.dto.EdcationDTO;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserTable;
import com.service_exchange.utal.PageToListConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Altysh
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, params = "start")
    private List<UserTable> getAllChanges(Integer start) {

        return PageToListConverter.convertList(service.getAllUser((start != null) ? start : 0));
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    private List<UserTable> getAllChanges() {
        
        return service.getAllUser();
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    private UserTable createUser(@RequestBody UserTable user) {

        return service.createUser(user);
    }

//    @RequestMapping(value = "/addChallange", method = RequestMethod.POST)
//    private Boolean addChallangeToUser(@RequestBody Map<String, Integer> json) {
//        
//        return service.addChallangeToUser(json.get("challangeId"), json.get("userId"));
//    }
//
//    @RequestMapping(value = "/removeChallange", method = RequestMethod.POST)
//    private Boolean removeChallangeToUser(@RequestBody Map<String, Integer> json) {
//
//        return service.removeChallangeToUser(json.get("challangeId"), json.get("userId"));
//    }

     //---userBadge services
    
    @RequestMapping(value = "/getAllUserBadges/{userId}", method = RequestMethod.GET)
    private List<Badge> getAllUserBadges(@PathVariable Integer userId) {
        
        return service.getAllUserBadges(userId);
    }
    
    @RequestMapping(value = "/getAllUserBadges/{userId}/{pageNum}", method = RequestMethod.GET)
    private List<Badge> getAllUserBadges(@PathVariable Integer userId,@PathVariable Integer pageNum) {
        
        return service.getAllUserBadges(userId,pageNum);    
    }
    
    @RequestMapping(value = "/assignBadgeToUser", method = RequestMethod.POST)
    private boolean assignBadgeToUser(@RequestBody Map<String, Object> json) {

        //l hyb3t hyb3t json feh 2 objects wa7ed b asm user wl tany b asm badge
//        UserTable user= (UserTable) json.get("user");
//        Badge badge=(Badge) json.get("badge");
//        return service.assignBadgeToUser(user, badge);
return false;
    }
    
     //---userBadge services

    //mubarak//
    @RequestMapping(value = "/logInOrSignup", method = RequestMethod.POST)
    private UserDTO loginOrSignUp(@RequestBody UserDTO user) {

        return service.logInORSignUp(user);
    }

    @RequestMapping(value = "/getUserServices", method = RequestMethod.GET)
    private List<ServiceDTO> getUserServices(@RequestParam Integer userId) {
        return service.getUserService(userId);
    }

    @RequestMapping(value = "/getUserSkills", method = RequestMethod.GET)
    private List<SkillDTO> getUserSkills(@RequestParam Integer userId) {
        return service.getUserSkill(userId);
    }

    @RequestMapping(value = "/getUserEducation", method = RequestMethod.GET)
    private List<EdcationDTO> getUserEducation(@RequestParam Integer userId) {
        return service.getUserEdcation(userId);
    }

    //mubarak//
}
