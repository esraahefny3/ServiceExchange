/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer;

import com.service_exchange.api_services.dao.challanges.ChallangeDao;
import com.service_exchange.api_services.dao.challanges.ChallangeInterFace;
import com.service_exchange.entities.Challenge;
import com.service_exchange.entities.UserTable;
import com.service_exchange.utal.UnoptimizedDeepCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Altysh
 */
@Component
public class ChallangeService implements ChallangeInterFace {

    @Autowired
    ChallangeDao challangeDao;

    @Override
    public boolean isChallangeComplete(Challenge challenge, UserTable user) {
        return challangeDao.isChallangeComplete(challenge, user);
    }

    @Override
    public double getChallangeState(Challenge challenge, UserTable user) {
        return challangeDao.getChallangeState(challenge, user);
    }

    @Override
    public Challenge createChallange(Challenge challenge) {
        Challenge challenge1 = challangeDao.createChallange(challenge);
        return challenge1;
    }

    @Override
    public Challenge updateChallange(Challenge challenge) {
        Challenge challenge1 = challangeDao.updateChallange(challenge);
        return challenge1;
    }

    @Override
    public boolean deleteChallange(Challenge challenge) {
        return challangeDao.deleteChallange(challenge);
    }

    @Override
    public List<Challenge> getAllChallanges() {

        List<Challenge> challenge1 = challangeDao.getAllChallanges();
        List<Challenge> challenges2 = new LinkedList<>();
        challenge1.stream().map((t) -> {

            return t;
        }).forEach(t -> challenges2.add(t));
        return challenges2;
    }

    @Override
    public List<Challenge> getUserChallanges(UserTable user) {
        List<Challenge> challenge1 = challangeDao.getUserChallanges(user);
        List<Challenge> challenges2 = new LinkedList<>();
        challenge1.stream().map((t) -> {

            return t;
        }).forEach(t -> challenges2.add(t));
        return challenges2;
    }

    @Override
    public Challenge getChallange(Integer id) {
        Challenge ch = challangeDao.getChallange(id);
        if (ch != null) {
            return (Challenge) UnoptimizedDeepCopy.copy(ch);
        }else return null;
    }

}
