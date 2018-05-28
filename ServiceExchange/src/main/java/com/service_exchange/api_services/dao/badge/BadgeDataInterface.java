/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.badge;

import com.service_exchange.entities.Badge;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author esraa
 */
@Repository 
public interface BadgeDataInterface extends  PagingAndSortingRepository<Badge,Integer>  {
  
}
