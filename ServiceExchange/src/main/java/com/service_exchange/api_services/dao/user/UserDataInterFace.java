/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user;

import com.service_exchange.entities.Service;
import com.service_exchange.entities.UserTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Altysh
 */
@Repository
public interface UserDataInterFace extends PagingAndSortingRepository<UserTable, Integer> {

    Page<UserTable> findByNameContains(String name, Pageable page);

    UserTable findByAccountIdEquals(String accountId);

    Long countAllByIdIsNotNull();

    Long countAllByStatusEquals(String status);


}

interface u extends PagingAndSortingRepository<Service, Integer> {
    Page<Service> findAllByTypeEqualsAndAvailableEquals(String type, String avalibe, Pageable page);

    Long countAllByAvailableEquals(String avalive);

}

