/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.restcontrollers;

import API.bussinesslayer.BadgeService;
import API.entities.Badge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    private BadgeService badgeService;
    @RequestMapping(value = "/createBadge", method = RequestMethod.POST)
public ResponseEntity<Badge> createBadge(@RequestBody Badge badge) {

    if(badge !=null)
    {
        badgeService.createBadge(badge);
    }
    return new ResponseEntity<Badge>(badge, HttpStatus.OK);
}
}
