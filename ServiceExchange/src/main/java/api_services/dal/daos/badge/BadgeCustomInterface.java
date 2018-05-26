/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_services.dal.daos.badge;

import entities.Badge;
import entities.User;

/**
 *
 * @author esraa
 */
public interface BadgeCustomInterface {
   public boolean isUserDeserveBadge(User user);
   public boolean assignBadgeToUser(User user,Badge badge);
}
