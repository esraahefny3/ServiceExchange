/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.challanges;

import com.service_exchange.entities.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Altysh
 */
@Repository
public interface ChallangeDataInteface extends  CrudRepository<Challenge,Integer>  {
    Page<Challenge> findAllByIsSuspendedEquals(Integer state, Pageable page);
}
