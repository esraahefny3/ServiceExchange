/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.userdelegates.userbadgedelegate;

import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserBadge;
import com.service_exchange.entities.UserTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 *
 * @author esraa
 */
@Component
public class UserBadgesImpl implements UserBadgesInterface {

    @Autowired
    private UserDataInterFace userDataInterface;

    @Override
    public List<Badge> getAllUserBadges(UserTable user) {
        return user.getUserBadgeCollection().stream()
                .map((t) -> t.getBadge()).collect(Collectors.toList());

    }

    @Override
    public List<Badge> getAllUserBadges(UserTable user, Pageable page) {

//        List<UserBadge> userBadgesList = userDataInterface.findByuserTable(user, page);
//        return userBadgesList.stream()
//                .map((t) -> t.getBadge()).collect(Collectors.toList());
return null;
    }

    @Override
    public boolean assignBadgeToUser(UserTable user, Badge Badge) {
//        try {
//            user.addBadge(userBadge);
//            userDataInterface.save(user);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
    return true;
    }

    @Override
    public UserTable checkIfUserExist(Integer userId) {
        Optional<UserTable> userOptional = userDataInterface.findById(userId);
        if (userOptional.isPresent()) {
            UserTable userObj = userOptional.get();
            return userObj;
        } else {
            return null;
        }
    }

}
