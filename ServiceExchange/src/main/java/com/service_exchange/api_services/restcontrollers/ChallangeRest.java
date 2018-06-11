/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.restcontrollers;

import com.service_exchange.api_services.bussinesslayer.ChallangeService;
import com.service_exchange.entities.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Altysh
 */
@RestController
@RequestMapping("/challange")
public class ChallangeRest {
    @Autowired
    ChallangeService service;
    @RequestMapping("/getAll")
    private List<Challenge> getAllChanges(Integer page) {
        return service.getAllChallanges(page);
    }
    @RequestMapping(value = "/createChallange" ,method = RequestMethod.POST)
   private Challenge createChallange(@RequestBody Challenge challenge){
        return service.createChallange(challenge);
    }
    @RequestMapping(value = "/updateChallange",method = RequestMethod.POST)
   private Challenge updateChallange(@RequestBody Challenge challenge){
        return service.updateChallange(challenge);
    }
}
