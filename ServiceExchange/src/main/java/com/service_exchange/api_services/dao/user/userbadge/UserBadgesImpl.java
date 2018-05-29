/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userbadge;

import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.entities.Badge;
import com.service_exchange.entities.UserBadge;
import com.service_exchange.entities.UserTable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author esraa
 */
public class UserBadgesImpl implements UserBadgesInterface{

    @Autowired
    private UserDataInterFace dataInterface;

    @Override
    public List<Badge> getAllUserBadge(UserTable userTable, Pageable page) {
            List badgesList=new ArrayList();
          //  List userBadgesLis=userTable.get
          return badgesList;
    }

    @Override
    public boolean assignBadgeToUser(UserTable user,UserBadge userBadge) {
          try{
              user.addBadge(userBadge);
              dataInterface.save(user);
              return true;
          }
          catch(Exception e)
          {
              return false;
          }
    }


    
}
