/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user;

import com.service_exchange.entities.UserTable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author Altysh
 */
@Component
public interface UserDataInterFace extends PagingAndSortingRepository<UserTable,Integer>{
   
    Page<UserTable> findByNameContains(String name ,Pageable page );
}
