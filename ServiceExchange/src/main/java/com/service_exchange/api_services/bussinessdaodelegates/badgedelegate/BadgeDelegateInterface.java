/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.badgedelegate;

import com.service_exchange.entities.Badge;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author esraa
 */

public interface BadgeDelegateInterface {
    public boolean checkIfBadgeExist(Badge badge);
    public Badge getBadge(Integer id);
    public Badge  createBadge(Badge badge);
    public boolean deleteBadge(Badge badge);
    public Badge updateBadge(Badge badge);
    public Page<Badge> getAllBadges(Pageable page);
    public Iterable<Badge> getAllBadges();
    
}
