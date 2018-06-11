/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.challanges;

import com.service_exchange.entities.Challenge;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 *
 * @author Altysh
 */
@Component
public class ChallangeDao implements ChallangeInterFace {

    @Autowired
    ChallangeDataInteface dataInteface;

    @Override
    public boolean isChallangeComplete(Challenge challenge, UserTable user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getChallangeState(Challenge challenge, UserTable user) {
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
    public List<Challenge> getAllChallanges(Integer page) {
        return dataInteface.findAllByIsSuspendedEquals(Challenge.RESUMED, PageRequest.of(page, 20)).stream().collect(Collectors.toList());

    }

    private boolean isChallangeTouchable(Challenge c) {
        AtomicReference<Challenge> ch = new AtomicReference<>();
        dataInteface.findById(c.getId()).ifPresent(ch::set);
        return Objects.requireNonNull(ch.get()).getUserChallengeCollection().size() <= 0;
    }

    private boolean isChallangeExit(Challenge c) {
        return dataInteface.existsById(c.getId());
    }

    @Override
    public List<Challenge> getUserChallanges(UserTable user) {

        List<Challenge> list = new ArrayList<>();
        dataInteface.findAllById(Objects.requireNonNull(user.getUserChallengeCollection()).stream().map(userChallenge -> userChallenge.getChallenge().getId())
                .collect(Collectors.toList())).forEach(list::add);
        return list;
    }

    @Override
    public Challenge getChallange(Integer id) {
        Optional<Challenge> ch = dataInteface.findById(id);
        return ch.orElse(null);
    }

}
