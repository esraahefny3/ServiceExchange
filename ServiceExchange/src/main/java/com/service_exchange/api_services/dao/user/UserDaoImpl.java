/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user;

import com.service_exchange.api_services.dao.challanges.ChallangeDao;
import com.service_exchange.entities.Challenge;
import com.service_exchange.entities.TransactionInfo;

import com.service_exchange.entities.UserTable;
import com.service_exchange.utal.UnoptimizedDeepCopy;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Altysh
 */
@Component
public class UserDaoImpl implements UserInterFace {

    @Autowired
    private UserDataInterFace dataInterface;

    @Autowired
    private ChallangeDao challangeDao;

    @Override
    public UserTable createUser(UserTable user) {
        UserTable muser = dataInterface.save(user);
        //  System.out.println(muser.getName());
        UserTable nUser = (UserTable) UnoptimizedDeepCopy.copy(user);
        //nUser.getName();
        return nUser;
    }

    @Override
    public UserTable updateUser(UserTable user) {
        return (UserTable) UnoptimizedDeepCopy.copy(dataInterface.save(user));
    }

    @Override
    public Boolean checkEmailAvalible(Integer email) {
        return dataInterface.findById(email).isPresent();
    }

    @Override
    @Transactional
    public Page<UserTable> getAllUser(int start) {

        Page<UserTable> us = dataInterface.findAll(new PageRequest(start, 20));

        return us;
    }
    public List<UserTable> getAllUser(){
        List<UserTable> list = new LinkedList<>();
        dataInterface.findAll().forEach((t) -> {
            list.add(t);
           // System.out.println(t.getComplaintCollection());
        });
        return list;
    }

    @Override
    public Page<UserTable> scerchUserByName(String name, int start) {
        return (Page<UserTable>) UnoptimizedDeepCopy.copy(dataInterface.findByNameContains(name, new PageRequest(start, start + 20)));

    }

    @Override
    public Long getDuration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getDurtation(UserTable user, long start, long end) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TransactionInfo> getSuccsfullTransaction(UserTable user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean addChallangeToUser(Integer chId, Integer userId) {
        Optional<UserTable> user = dataInterface.findById(chId);
        if (user.isPresent()) {
            return user.get().addChallange(chId);

        } else {
            return false;
        }
    }

    @Override
    public Boolean removeChallangeToUser(Integer chId, Integer userId) {
        Optional<UserTable> user = dataInterface.findById(chId);
        Challenge ch = challangeDao.getChallange(chId);
        if (user.isPresent() && ch != null) {
            return user.get().removeChallange(chId);
        } else {
            return false;
        }
    }

}
