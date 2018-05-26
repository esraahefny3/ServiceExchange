/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_services.dal.daos.badge;

import entities.Badge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author esraa
 */
@Component
public interface BadgeDataInterface extends  CrudRepository<Badge,Integer>  {
  
}
