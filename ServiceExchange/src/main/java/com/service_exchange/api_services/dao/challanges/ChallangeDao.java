/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.challanges;

import com.service_exchange.entities.Challenge;
import com.service_exchange.entities.User;
import com.service_exchange.entities.UserChallenge;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Altysh
 */
@Component
public class ChallangeDao implements ChallangeInterFace {

    @Autowired
    ChallangeDataInteface dataInteface;

    @Override
    public boolean isChallangeComplete(Challenge challenge, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getChallangeState(Challenge challenge, User user) {
        //Optional<UserChallenge> ob = user.getUserChallengeCollection().stream().filter(e -> e.getChallenge().getId().equals(challenge.getId())).findAny();
//        if (ob.isPresent()) {
//            return 0.0;
//        }
        return 0.0;
    }

    @Override
    public Challenge createChallange(Challenge challenge) {
        Challenge c = challenge;
        if (challenge.getId() != null) {
            if (!isChallangeExit(challenge)) {
                c = dataInteface.save(challenge);
            }
        }
        return c;
    }

    @Override
    public Challenge updateChallange(Challenge challenge) {
        if (isChallangeTouchable(challenge)) {
            return dataInteface.save(challenge);
        }
        return challenge;
    }

    @Override
    public boolean deleteChallange(Challenge challenge) {
        boolean b = isChallangeTouchable(challenge);
        if (b) {
            dataInteface.delete(challenge);

        }
        return b;
    }

    @Override
    public List<Challenge> getAllChallanges() {
        List<Challenge> list = new LinkedList();
        dataInteface.findAll().forEach(e -> list.add(e));
        return list;
    }

    private boolean isChallangeTouchable(Challenge c) {
        Challenge ch = dataInteface.findById(c.getId()).get();
        return ch.getUserChallengeCollection().size() <= 0;
    }

    private boolean isChallangeExit(Challenge c) {
        return dataInteface.existsById(c.getId());
    }

    @Override
    public List<Challenge> getUserChallanges(User user) {
        List<Integer> list = new LinkedList<>();
       // Integer[] a = (Integer[]) user.getUserChallengeCollection().stream().map(e -> e.getChallenge().getId()).toArray();

        List<Challenge> ucs = new LinkedList<>();
        dataInteface.findAllById(Arrays.asList(new Integer(0))).forEach(e -> ucs.add(e));
        return ucs;
    }

    @Override
    public Challenge getChallange(Integer id) {
        Optional<Challenge> ch = dataInteface.findById(id);
        if (ch.isPresent()) {
            return ch.get();
        } else {
            return null;
        }
    }

}
