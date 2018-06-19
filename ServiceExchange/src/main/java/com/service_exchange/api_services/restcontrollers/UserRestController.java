/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service_exchange.api_services.bussinesslayer.UserService;
import com.service_exchange.api_services.dao.dto.*;
import com.service_exchange.api_services.dao.transaction.TransactionDto;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Altysh
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserRestController {

    private final UserService service;


    @Autowired
    public UserRestController(UserService service) {

        if (service == null)
            System.out.println("null");
        this.service = service;


    }

    //  @PreAuthorize("hasRole('admin_view')")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, params = "start")
    private List<UserDTO> getAllChanges(Integer start) {

        return service.getAllUser(start);
    }

    //@PreAuthorize("hasRole('admin_view')")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    private List<UserDTO> getAllChanges() {

        return service.getAllUser();
    }

    //@PreAuthorize("hasRole('admin_view')")
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    private UserTable createUser(@RequestBody UserDTO user) {

        return service.createUser(AppFactory.mapToDto(user, UserTable.class));
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
     @RolesAllowed({"admin_view", "User"})
    @RequestMapping(value = "/getAllUserBadges/{userId}", method = RequestMethod.GET)
    private List<Badge> getAllUserBadges(@PathVariable Integer userId) {
        
        return service.getAllUserBadges(userId);
    }

    @RolesAllowed({"admin_view", "User"})
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

    @RolesAllowed({"admin_view", "User"})
    @RequestMapping(value = "/getUserServices", method = RequestMethod.GET)
    private List<ServiceDTO> getUserServices(@RequestParam Integer userId, @RequestParam String type) {
        return service.getUserService(userId, type);
    }

    @RolesAllowed({"admin_view", "User"})
    @RequestMapping(value = "/getUserSkills", method = RequestMethod.GET)
    private List<SkillDTO> getUserSkills(@RequestParam Integer userId) {
        return service.getUserSkill(userId);
    }

    @RolesAllowed({"admin_view", "User"})
    @RequestMapping(value = "/getUserEducation", method = RequestMethod.GET)
    private List<EdcationDTO> getUserEducation(@RequestParam Integer userId) {

        return service.getUserEdcation(userId);

    }

    @RequestMapping(value = "/addEmail", method = RequestMethod.POST)
    private Boolean addEmail(@RequestBody Map<String, Object> map) {
        return service.addEmail((String) map.get("email"), (Integer) map.get("userId"));
    }

    @RequestMapping(value = "/addSkill", method = RequestMethod.POST)
    private Boolean addSkill(@RequestBody Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        SkillDTO skillDTO1 = mapper.convertValue(map.get("skillDto"), SkillDTO.class);
        return service.addSkill(skillDTO1, (Integer) map.get("userId"));
    }

    @RequestMapping(value = "/addTelephone", method = RequestMethod.POST)
    private Boolean addTelephone(@RequestBody Map<String, Object> map) {
        return service.addTelephone((String) map.get("telephone"), (Integer) map.get("userId"));
    }

    @RequestMapping(value = "/removeTelephone", method = RequestMethod.POST)
    private Boolean removeTelephone(@RequestBody Map<String, Object> map) {
        return service.removeTelephone((String) map.get("telephone"), (Integer) map.get("userId"));
    }

    @RequestMapping(value = "/removeEmail", method = RequestMethod.POST)
    private Boolean removeEmail(@RequestBody Map<String, Object> map) {
        return service.removeEmail((String) map.get("email"), (Integer) map.get("userId"));
    }

    @RequestMapping(value = "/removeSkill", method = RequestMethod.POST)
    private Boolean removeSkill(@RequestBody Map<String, Object> map) {
        return service.removeSkill((Integer) map.get("skillId"), (Integer) map.get("userId"));
    }

    @RequestMapping(value = "/removeService", method = RequestMethod.POST)
    private Boolean removeService(@RequestBody Map<String, Object> map) {
        return service.removeService((Integer) map.get("serviceId"), (Integer) map.get("userId"), (Boolean) map.get("forced"));
    }

    @RequestMapping(value = "/addService", method = RequestMethod.POST)
    private ServiceDTO addService(@RequestBody ServiceDTO serviceDTO) {

        return service.addService(serviceDTO);
    }

    @RequestMapping(value = "/getUserStatic", method = RequestMethod.GET)
    private UserStatics getUserStatic(@RequestParam Integer userId) {

        return service.getUserStatic(userId);
    }

    @RequestMapping(value = "/getEarningList", method = RequestMethod.GET)
    private List<EarningListObject> getEarningList(@RequestParam Integer userId) {
        return service.getEaringList(userId);
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    private UserInfo getUserInfo(@RequestParam Integer userId) {
        return service.getUserInfo(userId);
    }

    @RequestMapping(value = "/setUserFirebaseId", method = RequestMethod.POST)
    private Boolean setUserFirebaseId(@RequestBody Map<String, Object> map) {

        return service.setUserFireBase((Integer) map.get("userId"), (String) map.get("firebaseId"),(String) map.get("type"));
    }

    @RequestMapping(value = "/getUserProfileData", method = RequestMethod.GET)
    private HodaProfile getUserProfileData(Integer userId) {

        return service.getData(userId);
    }

    @RequestMapping(value = "/updateUserService", method = RequestMethod.POST)
    private Boolean updateService(@RequestBody ServiceDTO serviceDTO) {
        return service.updateService(serviceDTO);
    }

    @RequestMapping(value = "/updateUserData", method = RequestMethod.POST)
    private Boolean updateUser(@RequestBody UserDTO userDTO) {


        return service.updataUser(userDTO);
    }

    @RequestMapping(value = "/getUserIncomingReq", method = RequestMethod.GET)
    private List<TransactionDto> getUserIncomingReq(Integer userId) {
        return service.getAllUserTransAction(userId);
    }
    //mubarak//


    //hoda//
    @RequestMapping(value = "/getTopUsersWeb", method = RequestMethod.GET)
    private List<UserWEB> getTopUsers(Integer size) {
        return service.getTopUserWeb(size);
    }

    @RequestMapping(value = "/getUserData", method = RequestMethod.GET)
    private UserDataWEB getUserData(Integer userId) {
        return service.getUserData(userId);
    }

}
