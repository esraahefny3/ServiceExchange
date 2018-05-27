/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.challanges.dao;

import com.service_exchange.entities.Challenge;
import com.service_exchange.entities.User;
import java.util.List;

/**
 *
 * @author Altysh
 */
public interface ChallangeInterFace {
    boolean isChallangeComplete(Challenge challenge,User user);
    double getChallangeState(Challenge challenge,User user);
    Challenge createChallange(Challenge challenge);
    Challenge updateChallange(Challenge challenge);
    boolean deleteChallange(Challenge challenge);
    List<Challenge> getAllChallanges();
    List<Challenge> getUserChallanges(User user);
    
    
    
}
