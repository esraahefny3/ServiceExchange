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
import com.service_exchange.api_services.dao.dto.*;
import com.service_exchange.api_services.dao.user.UserDaoImpl;
import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserTable;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Altysh
 */
@Service
public class UserService {
    @Autowired
    private UserDaoImpl daoImpl;


    //---user badge
    @Autowired
    private UserBadgesSelegateInterface userBadgesInterface;
    private int pageSize = 20;

    public UserTable createUser(UserTable user) {
        return daoImpl.createUser(user);
    }

    public String getSome() {
        if (daoImpl == null)
            return "hello";
        else return "good";
    }


    //--user badge
    //mubarak//
    @Autowired
    private UserDataGet userDataGet;

    public List<UserDTO> getAllUser() {
        return userDataGet.getAllUser();
    }

    public UserTable updateUser(UserTable user) {
        return daoImpl.updateUser(user);
    }

    public Boolean checkEmailAvalible(Integer email) {
        return daoImpl.checkEmailAvalible(email);
    }
    //static

    public Page<UserTable> scerchUserByName(String name, int start) {
        return daoImpl.scerchUserByName(name, start);
    }

    @Autowired
    private UserStaticsGetter userStaticsGetter;

    @Nullable
    public String getUserLevel(int userId) {
        UserTable userTable = userDataGet.getUserById(userId);
        String level = null;
        if (userTable != null) {
            level = userTable.getUserBadgeCollection().stream().filter(userBadge -> {
                        Badge b = userBadge.getBadge().getNextLevel();
                        if (b != null) {

                            return b.getUserBadgeCollection().stream().noneMatch(userBadge1 -> userBadge1.getUserBadgePK().getUserId() == userId);
                        } else
                            return true;
                    }
            ).findFirst().map(userBadge -> userBadge.getBadge().getName()).orElse("not found");

        }
        return level;
    }

    @Nullable
    public String getUserNextLevel(int userId) {
        UserTable userTable = userDataGet.getUserById(userId);
        String level = null;
        if (userTable != null) {
            level = userTable.getUserBadgeCollection().stream().filter(userBadge -> {
                        Badge b = userBadge.getBadge().getNextLevel();
                        if (b != null) {

                            return b.getUserBadgeCollection().stream().noneMatch(userBadge1 -> userBadge1.getUserBadgePK().getUserId() == userId);
                        } else
                            return false;
                    }
            ).findFirst().map(userBadge -> userBadge.getBadge().getNextLevel().getDescription()).orElse("not found");

        }
        return level;
    }

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
            userStatics.setNumberOfUnreadedMessage(0);
            userStatics.setOnTimeDelivery(userStaticsGetter.getOnTimeDelevrey(userId));
            userStatics.setOrderCompletion(userStaticsGetter.getOrderCompletion(userId));
            userStatics.setPresonalBalance(userStaticsGetter.getUserBalance(userId));
            userStatics.setResponseRate(userStaticsGetter.getResponceRate(userId));
            userStatics.setResponseTime(userStaticsGetter.getResponceMessageTime(userId));
            return userStatics;
        }
        return null;
    }
    /*
     Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
    */

    public List<EarningListObject> getEaringList(Integer userId) {
        UserTable userTable = userDataGet.getUserById(userId);
        final List<EarningListObject> list = new ArrayList<>();
//        if (userTable != null) {
//            userTable.getServiceCollection().stream().filter(service -> service.getType().equals(com.service_exchange.entities.Service.OFFERED))
//                    .forEach(service ->
//                            service.getTransactionInfoCollection().stream().filter(transactionInfo -> transactionInfo.getState().equals(TransactionInfo.ACCEPTED_STATE)
//                                    || transactionInfo.getState().equals(TransactionInfo.COMPLETED_STATE) || transactionInfo.getState().equals(TransactionInfo.LATE_STATE))
//                                    .forEach(transactionInfo -> list.add(new EarningListObject(transactionInfo.getId(),
//                                            transactionInfo.getServiceId().getName()
//                                            , transactionInfo.getStartDate().getTime()
//                                            + transactionInfo.getDuration().longValue(), transactionInfo.getPrice())))
//                    );
//
//        }
        return userStaticsGetter.getEaringList(userId);
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

    public List<UserDTO> getAllUser(int start) {

        return userDataGet.getAllUser(start);
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

    //mubarak//
    @Autowired
    private UserDataSet userDataSet;
    @Autowired
    private UserDataDelete userDataDelete;


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


    public Boolean addEmail(String email, Integer userId) {
        return userDataSet.addEmailToUser(email, userId);
    }

    public Boolean addSkill(SkillDTO skillDTO, Integer userId) {
        return userDataSet.addSkillToUser(skillDTO, userId);
    }

    public ServiceDTO addService(ServiceDTO serviceDTO) {
        return userDataSet.addServiceToUser(serviceDTO);
    }

    public Boolean addTelephone(String telephone, Integer userId) {
        return userDataSet.addTelephonToUser(telephone, userId);
    }

    public Boolean removeTelephone(String telephone, Integer userId) {
        return userDataDelete.removeTelePhoneFormUser(telephone, userId);
    }

    public Boolean removeEmail(String email, Integer userId) {
        return userDataDelete.removeEmailFormUser(email, userId);
    }

    public Boolean removeSkill(Integer skillId, Integer userId) {
        return userDataDelete.removeSkillFormUser(skillId, userId);
    }

    public Boolean removeService(Integer service, Integer userId, Boolean forced) {
        return userDataDelete.removeServiceToUser(userId, service, forced);
    }


    //mubarak//


}
