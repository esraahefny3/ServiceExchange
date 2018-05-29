/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userChallange;

import com.service_exchange.entities.Challenge;

import java.util.List;

/**
 *
 * @author Altysh
 */
public interface UserChallangeInterFace {

    List<Challenge> getAllChallangeForUser(Integer userId);

    Boolean addChallangeToUser(Integer chId, Integer userId);

    Boolean removeChallangeToUser(Integer chId, Integer userId);
}
