/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user;

import com.service_exchange.entities.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author Altysh
 */
@Component
public interface UserDataInterFace extends CrudRepository<User,Integer>{
    Page<User> findAll(Pageable page );
    Page<User> findByNameContains(String name ,Pageable page );
}
