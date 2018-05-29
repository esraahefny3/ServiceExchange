/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.restcontrollers;

import com.service_exchange.api_services.bussinesslayer.UserService;
import com.service_exchange.entities.UserTable;
import com.service_exchange.utal.PageToListConverter;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Altysh
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET,params = "start")
    private List<UserTable> getAllChanges( Integer start) {
     
        return PageToListConverter.convertList(service.getAllUser((start != null)?start:0));
    }
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    private List<UserTable> getAllChanges() {
        //System.out.println("the right one");
        return service.getAllUser();
    }
      @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    private UserTable createUser(@RequestBody UserTable user) {
     
        return service.createUser(user);
    }
     @RequestMapping(value = "/addChallange",method = RequestMethod.POST)
    private Boolean addChallangeToUser( @RequestBody Map<String, Integer> json) {
         System.out.println(json);
        return service.addChallangeToUser( json.get("challangeId"),json.get("userId"));
    }
     @RequestMapping(value = "/removeChallange",method = RequestMethod.POST)
    private Boolean removeChallangeToUser(@RequestBody Map<String, Integer> json) {
     
        return service.removeChallangeToUser(json.get("challangeId"),json.get("userId"));
    }
    
    
}
