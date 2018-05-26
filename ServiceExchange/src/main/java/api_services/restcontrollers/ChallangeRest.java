/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_services.restcontrollers;

import api_services.bussinesslayer.ChallangeService;
import entities.Challenge;
import java.util.List;
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
public class ChallangeRest {
    @Autowired
    ChallangeService service;
    @RequestMapping("/getAll")
   private List<Challenge> getAllChanges(){
        return service.getAllChallanges();
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
