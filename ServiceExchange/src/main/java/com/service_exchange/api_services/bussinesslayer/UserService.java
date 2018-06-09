/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer;

import com.service_exchange.api_services.bussinessdaodelegates.user.UserDataDelete;
import com.service_exchange.api_services.bussinessdaodelegates.user.UserDataGet;
import com.service_exchange.api_services.bussinessdaodelegates.user.UserDataSet;
import com.service_exchange.api_services.bussinessdaodelegates.userdelegates.userbadgedelegate.UserBadgesSelegateInterface;
import com.service_exchange.api_services.dao.dto.EdcationDTO;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.service_exchange.api_services.dao.user.UserDaoImpl;
import com.service_exchange.entities.Badge;
import com.service_exchange.entities.Review;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    public double getEarningInMonth(int userId) {
        UserTable userTable = userDataGet.getUserById(userId);
        Calendar localDate = Calendar.getInstance();
        localDate.set(Calendar.DAY_OF_MONTH, 1);
        if (userTable != null) {
            return userTable.getServiceCollection().stream().filter(service -> service.getType().equals(com.service_exchange.entities.Service.OFFERED))
                    .mapToInt(value -> value.getTransactionInfoCollection().stream().filter(transactionInfo -> !transactionInfo.getState().equals(TransactionInfo.REJECTED_STATE)
                            && !transactionInfo.getState().equals(TransactionInfo.PENDING_STATE))
                            .filter(transactionInfo -> (transactionInfo.getStartDate().getTime() + transactionInfo.getDuration().longValue()) >= localDate.getTimeInMillis())

                            .mapToInt(TransactionInfo::getPrice).sum()
                    ).sum();
        } else return 0;
    }

    public int getUserBalance(int userId) {
        UserTable userTable = userDataGet.getUserById(userId);
        Calendar localDate = Calendar.getInstance();
        localDate.set(Calendar.DAY_OF_MONTH, 1);
        if (userTable != null) {
            return userTable.getBalance();
        } else return 0;
    }

    private double orderCompletion(Integer userId) {
        UserTable userTable = userDataGet.getUserById(userId);
        AtomicReference<Double> d = new AtomicReference<>((double) 0);
        if (userTable != null) {
            userTable.getServiceCollection().stream().filter(service -> service.getType().equals(com.service_exchange.entities.Service.OFFERED))
                    .mapToDouble(service -> {
                        service.getTransactionInfoCollection().stream().filter(transactionInfo -> !transactionInfo.getState().equals(TransactionInfo.REJECTED_STATE)
                                && !transactionInfo.getState().equals(TransactionInfo.PENDING_STATE))
                                .mapToDouble(value -> {
                                    System.out.println(value.getId());
                                    if (value.getState().equals(TransactionInfo.ACCEPTED_STATE) || value.getState().equals(TransactionInfo.ON_PROGRESS_STATE)) {
                                        return 0.0;
                                    } else return 1.0;
                                }).average().ifPresent(d::set);
                        return d.get();
                    }).average().ifPresent(d::set);

        }
        return d.get();
    }

    private double onTimeDelevrey(Integer userId) {
        UserTable userTable = userDataGet.getUserById(userId);
        AtomicReference<Double> d = new AtomicReference<>((double) 0);
        if (userTable != null) {
            userTable.getServiceCollection().stream().filter(service -> service.getType().equals(com.service_exchange.entities.Service.OFFERED))
                    .mapToDouble(service -> {
                        service.getTransactionInfoCollection().stream().filter(transactionInfo -> transactionInfo.getState().equals(TransactionInfo.COMPLETED_STATE)
                                || transactionInfo.getState().equals(TransactionInfo.EXTENDED_STATE)
                                || transactionInfo.getState().equals(TransactionInfo.LATE_STATE))
                                .mapToDouble(value -> {
                                    if (value.getState().equals(TransactionInfo.LATE_STATE) || value.getState().equals(TransactionInfo.EXTENDED_STATE)) {
                                        return 0.0;
                                    } else return 1.0;
                                }).average().ifPresent(d::set);
                        return d.get();
                    })
                    .average().ifPresent(d::set);

        }
        return d.get();
    }

    private double totalFeedBack(Integer userId) {
        UserTable userTable = userDataGet.getUserById(userId);
        AtomicReference<Double> d = new AtomicReference<>((double) 0);
        if (userTable != null) {
            userTable.getServiceCollection().stream().filter(service -> service.getType().equals(com.service_exchange.entities.Service.OFFERED))
                    .mapToDouble(service -> {
                        service.getTransactionInfoCollection().stream().filter(transactionInfo -> transactionInfo.getState().equals(TransactionInfo.COMPLETED_STATE)
                                || transactionInfo.getState().equals(TransactionInfo.EXTENDED_STATE)
                                || transactionInfo.getState().equals(TransactionInfo.LATE_STATE))
                                .mapToDouble(value -> {
                                    final double[] val = {0};
                                    value.getReviewCollection().stream().mapToDouble(Review::getRating).average().ifPresent(value1 -> val[0] = value1 / 5);
                                    return val[0];
                                }).average().ifPresent(d::set);
                        return d.get();
                    })
                    .average().ifPresent(d::set);

        }
        return d.get();
    }

    private double getResponceMessageTime(Integer userId) {
        UserTable userTable = userDataGet.getUserById(userId);
        final double[] retval = {0};
        if (userTable != null) {
            userTable.getServiceCollection().stream().filter(service -> service.getType().equals(com.service_exchange.entities.Service.OFFERED))
                    .map(service -> service.getTransactionInfoCollection().stream())
                    .mapToDouble(transactionInfoStream -> {
                        final double[] dval = {0};
                        transactionInfoStream.mapToDouble(value -> value.getMessageCollection().stream().filter(message -> message.getSenderId().getId().intValue() != userId.intValue())
                                .mapToDouble(value1 -> value1.getSeenDate().getTime()).sorted().reduce(0, (left, right) -> right - left)).average().ifPresent(value -> dval[0] = value);
                        return dval[0];
                    }).average().ifPresent(value -> retval[0] = value);
            return retval[0];
        }
        return 0;
    }

    //static
    public List<Badge> getAllUserBadges(Integer userId, Integer pageNum) {
        if (userId != null && pageNum != null) {
            UserTable userNew = userBadgesInterface.checkIfUserExist(userId);
            if (userNew != null) {
                return userBadgesInterface.getAllUserBadges(userNew, PageRequest.of((pageNum != null) ? pageNum : 0, pageSize));
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
