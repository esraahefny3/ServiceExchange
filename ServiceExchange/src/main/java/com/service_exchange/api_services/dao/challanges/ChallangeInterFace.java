/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.challanges;

import com.service_exchange.entities.Challenge;
import com.service_exchange.entities.UserTable;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Altysh
 */
public interface ChallangeInterFace {
    boolean isChallangeComplete(Challenge challenge, UserTable user);

    double getChallangeState(Challenge challenge, UserTable user);

    @Nullable
    Challenge createChallange(Challenge challenge);

    @Nullable
    Challenge updateChallange(Challenge challenge);

    boolean deleteChallange(Challenge challenge);

    List<Challenge> getAllChallanges(Integer page);

    List<Challenge> getUserChallanges(UserTable user);

    @Nullable
    Challenge getChallange(Integer id);


}
