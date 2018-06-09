/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userbadge;

import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserBadge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author esraa
 */
@Component
public class User_Badges_Impl_Mubarak implements UserBadgesInterface {

    @Autowired
    private UserDataInterFace userDataInterface;

    @Autowired
    private UserBadeCrudRes userBadeCrudRes;
    @Override
    public List<Badge> getAllUserBadge(Integer userId, Pageable page) {
//           UserTable user = userDataInterface.findById(userId);
//        if (user != null) {
//            return user.getUserChallengeCollection().stream()
//                    .map((t) -> t.getChallenge()).collect(Collectors.toList());
//        }
      return null;
    }

    @Override
    public boolean assignBadgeToUser(int user, int userBadge) {
          try{
              UserBadge userBadge1 = new UserBadge(user, userBadge);
              userBadeCrudRes.save(userBadge1);
              return true;
          }
          catch(Exception e)
          {
              return false;
          }
    }


    
}
