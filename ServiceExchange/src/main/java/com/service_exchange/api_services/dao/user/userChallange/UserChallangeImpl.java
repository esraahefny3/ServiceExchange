/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userChallange;

import com.service_exchange.api_services.dao.user.UserInterFace;
import com.service_exchange.entities.Challenge;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Altysh
 */
@Component
public class UserChallangeImpl implements UserChallangeInterFace {

    @Autowired
    UserInterFace userInterFace;

    @Override
    public Boolean addChallangeToUser(Integer chId, Integer userId) {

        UserTable user = userInterFace.getUser(userId);

        if (user != null) {
            boolean state = user.addChallange(chId);
            if (state) {
                userInterFace.updateUser(user);

            }
            return state;
        }

        return false;
    }

    @Override
    public Boolean removeChallangeToUser(Integer chId, Integer userId) {
        UserTable user = userInterFace.getUser(userId);

        if (user != null) {
            boolean state = user.removeChallange(chId);
            if (state) {
                userInterFace.updateUser(user);

            }
            return state;
        }

        return false;
    }

    @Override
    public List<Challenge> getAllChallangeForUser(Integer userId) {
        UserTable user = userInterFace.getUser(userId);
        if (user != null) {
            return user.getUserChallengeCollection().stream()
                    .map((t) -> t.getChallenge()).collect(Collectors.toList());
        }
        return null;
    }

}
