/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_services.challanges.dao;

import entities.Challenge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Altysh
 */
@Repository
public interface ChallangeDataInteface extends  CrudRepository<Challenge,Integer>  {
    
}
