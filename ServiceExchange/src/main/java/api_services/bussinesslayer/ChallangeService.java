/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_services.bussinesslayer;

import api_services.challanges.dao.ChallangeDao;
import api_services.challanges.dao.ChallangeInterFace;
import entities.Challenge;
import entities.User;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Altysh
 */
@Component
public class ChallangeService implements ChallangeInterFace{
    @Autowired
    ChallangeDao challangeDao;
    
    @Override
    public boolean isChallangeComplete(Challenge challenge, User user) {
      return challangeDao.isChallangeComplete(challenge, user);
    }

    @Override
    public double getChallangeState(Challenge challenge, User user) {
   return challangeDao.getChallangeState(challenge, user);
    }

    @Override
    public Challenge createChallange(Challenge challenge) {
    Challenge challenge1 = challangeDao.createChallange(challenge);
    return challenge1.clone();
    }

    @Override
    public Challenge updateChallange(Challenge challenge) {
   Challenge challenge1 = challangeDao.updateChallange(challenge);
   return challenge1.clone();
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
       
       return t.clone(); 
   }).forEach(t->challenges2.add(t));
        return challenges2;
    }

    @Override
    public List<Challenge> getUserChallanges(User user) {
     List<Challenge> challenge1 = challangeDao.getUserChallanges(user);
    List<Challenge> challenges2 = new LinkedList<>();
    challenge1.stream().map((t) -> {
       
       return t.clone(); 
   }).forEach(t->challenges2.add(t));
        return challenges2;
    }
    
}
