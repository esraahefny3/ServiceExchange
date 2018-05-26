/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.dal.daos.badge;

import API.entities.Badge;
import API.entities.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author esraa
 */

@Component
public class BadgeCustomDAOImpl implements BadgeCustomInterface{

    @Override
    public boolean isUserDeserveBadge(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean assignBadgeToUser(User user, Badge badge) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
  
}
