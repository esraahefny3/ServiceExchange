/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.dal.daos.badge;

import API.entities.Badge;
import API.entities.User;

/**
 *
 * @author esraa
 */
public interface BadgeCustomInterface {
   public boolean isUserDeserveBadge(User user);
   public boolean assignBadgeToUser(User user,Badge badge);
}
