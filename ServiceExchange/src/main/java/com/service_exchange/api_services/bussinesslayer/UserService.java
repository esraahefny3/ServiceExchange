/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer;

import com.service_exchange.api_services.bussinessdaodelegates.user.UserDataDelete;
import com.service_exchange.api_services.bussinessdaodelegates.user.UserDataGet;
import com.service_exchange.api_services.bussinessdaodelegates.user.UserDataSet;
import com.service_exchange.api_services.bussinessdaodelegates.user.UserStaticsGetter;
import com.service_exchange.api_services.bussinessdaodelegates.userdelegates.userbadgedelegate.UserBadgesSelegateInterface;
import com.service_exchange.api_services.bussinesslayer.messagebussiness.MessageServiceInterface;
import com.service_exchange.api_services.dao.dto.*;
import com.service_exchange.api_services.dao.user.UserDaoImpl;
import com.service_exchange.api_services.dao.user.userTranaction.UserTransactionInterFace;
import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserTable;
import com.service_exchange.securty.UserSercurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * @author Altysh
 */
@Component
public class UserService {
    private final UserDaoImpl daoImpl;


    //---user badge
    private final UserBadgesSelegateInterface userBadgesInterface;
    private final MessageServiceInterface messageInterface;
    //--user badge
    //mubarak//
    private final UserDataGet userDataGet;
    private final UserStaticsGetter userStaticsGetter;
    //mubarak//
    private final UserDataSet userDataSet;

    public String getSome() {
        if (daoImpl == null)
            return "hello";
        else return "good";
    }

    private final UserDataDelete userDataDelete;
    private int pageSize = 20;
    UserTransactionInterFace u;
    @Autowired
    public UserService(UserDaoImpl daoImpl, UserBadgesSelegateInterface userBadgesInterface, MessageServiceInterface messageInterface, UserDataGet userDataGet, UserStaticsGetter userStaticsGetter, UserDataSet userDataSet, UserDataDelete userDataDelete) {
        this.daoImpl = daoImpl;
        this.userBadgesInterface = userBadgesInterface;
        this.messageInterface = messageInterface;
        this.userDataGet = userDataGet;
        this.userStaticsGetter = userStaticsGetter;
        this.userDataSet = userDataSet;
        this.userDataDelete = userDataDelete;

    }

    @Secured({"ROLE_Admin"})
    public UserTable createUser(UserTable user) {
        return daoImpl.createUser(user);
    }

    @Secured({"ROLE_Admin"})
    public UserDTO getUser(int userId) {
        return userDataGet.getUserByID(userId);
    }

    @Secured({"ROLE_Admin"})
    public List<UserDTO> getAllUser() {
        return userDataGet.getAllUser();
    }

    //static
    public List<Badge> getAllUserBadges(Integer userId, Integer pageNum) {
        if (userId != null && pageNum != null) {
            UserTable userNew = userBadgesInterface.checkIfUserExist(userId);
            if (userNew != null) {
                return userBadgesInterface.getAllUserBadges(userNew, PageRequest.of(pageNum, pageSize));
            }
        }
        return null;
    }

    public boolean assignBadgeToUser(UserTable user, Badge badge) {
        if (user != null && user.getId() != null && badge != null && badge.getId() != null) {
            return userBadgesInterface.assignBadgeToUser(user, badge);
        } else {
            return false;
        }
    }

    @Secured({"ROLE_Admin", "ROLE_User"})
    public UserStatics getUserStatic(int userId) {
        if (userDataGet.getUserById(userId) != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            UserStatics userStatics = new UserStatics();
            userStatics.setActiveOrder(userStaticsGetter.getActiveOrderCount(userId));
            userStatics.setAllUserPoint(userStaticsGetter.getUserBalanceFormTheStart(userId));
            userStatics.setAvgSellIng(userStaticsGetter.getAVGEarning(userId));
            userStatics.setCurrentLevel(userStaticsGetter.getUserLevel(userId));
            userStatics.setEarningInThisMounth(userStaticsGetter.getEarning(userId, calendar));
            userStatics.setFeedBackRate(userStaticsGetter.getTotalFeedBack(userId));
            userStatics.setNextLevelDescription(userStaticsGetter.getUserNextLevel(userId));
            userStatics.setNumberOfUnreadedMessage(messageInterface.selectAllUserUnreadedMessages(userId).size());
            userStatics.setOnTimeDelivery(userStaticsGetter.getOnTimeDelevrey(userId));
            userStatics.setOrderCompletion(userStaticsGetter.getOrderCompletion(userId));
            userStatics.setPresonalBalance(userStaticsGetter.getUserBalance(userId));
            userStatics.setResponseRate(userStaticsGetter.getResponceRate(userId));
            userStatics.setResponseTime(userStaticsGetter.getResponceMessageTime(userId));
            return userStatics;
        }
        return null;
    }

    public List<Badge> getAllUserBadges(Integer userId) {
        if (userId != null) {
            UserTable userNew = userBadgesInterface.checkIfUserExist(userId);
            if (userNew != null) {
                return userBadgesInterface.getAllUserBadges(userNew);
            }
        }
        return null;

    }

    /*
     Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
    */
    @Secured({"ROLE_Admin", "ROLE_User"})
    public List<EarningListObject> getEaringList(Integer userId) {
        UserTable userTable = userDataGet.getUserById(userId);
        final List<EarningListObject> list = new ArrayList<>();
        return userStaticsGetter.getEaringList(userId);
    }

    @Secured("ROLE_Admin")
    public List<UserDTO> getAllUser(int start) {

        return userDataGet.getAllUser(start);
    }

    public UserDTO logInORSignUp(UserDTO userDTO) {

        return userDataGet.loginOrSignUp(userDTO);
    }

    public List<SkillDTO> getUserSkill(Integer userId) {
        return userDataGet.getUserSkill(userId);
    }

    public List<EdcationDTO> getUserEdcation(Integer userId) {
        return userDataGet.getUserEdcation(userId);
    }

    public List<ServiceDTO> getUserService(Integer userId) {
        return userDataGet.getUserServices(userId);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_User') and @userService.isUser(authentication.principal,#userId)")
    public Boolean addEmail(String email, Integer userId) {
        return userDataSet.addEmailToUser(email, userId);
    }

    public boolean isUser(Object principal, Integer userId) {
        if (principal instanceof UserSercurity) {
            return Objects.equals(((UserSercurity) principal).getId(), userId);
        }

        return false;
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_User') and @userService.isUser(authentication.principal,#userId)")
    public Boolean addSkill(SkillDTO skillDTO, Integer userId) {
        return userDataSet.addSkillToUser(skillDTO, userId);
    }

    public ServiceDTO addService(ServiceDTO serviceDTO) {

        return userDataSet.addServiceToUser(serviceDTO);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_User') and @userService.isUser(authentication.principal,#userId)")
    public Boolean addTelephone(String telephone, Integer userId) {
        return userDataSet.addTelephonToUser(telephone, userId);
    }

    @Secured("ROLE_User")
    public Boolean removeTelephone(String telephone, Integer userId) {
        return userDataDelete.removeTelePhoneFormUser(telephone, userId);
    }

    @Secured("ROLE_User")
    public Boolean removeEmail(String email, Integer userId) {
        return userDataDelete.removeEmailFormUser(email, userId);
    }

    @Secured("ROLE_User")
    public Boolean removeSkill(Integer skillId, Integer userId) {
        return userDataDelete.removeSkillFormUser(skillId, userId);
    }

    @Secured("ROLE_User")
    public Boolean removeService(Integer service, Integer userId, Boolean forced) {
        return userDataDelete.removeServiceToUser(userId, service, forced);
    }

    public UserInfo getUserInfo(int userId) {
        return userDataGet.getUserInfoByID(userId);
    }

    public Boolean setUserFireBase(int userId, String firebaseId,String type) {
        return userDataSet.setUserFirebase(userId, firebaseId,type);
    }

    public HodaProfile getData(int userId) {
        return new HodaProfile(userDataGet.getLastActiveService(userId)
                , userDataGet.getLastPausedService(userId), userDataGet.getLastActiveReq(userId)
                , userDataGet.getLastCompletedReq(userId));
    }


    //mubarak//


}
