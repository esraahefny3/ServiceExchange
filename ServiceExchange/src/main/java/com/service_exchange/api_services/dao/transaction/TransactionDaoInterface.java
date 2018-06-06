/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.transaction;


import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author esraa
 */
@Repository
public interface TransactionDaoInterface extends  PagingAndSortingRepository<TransactionInfo,Integer>{

    //esraaaa
    @Query("select t from TransactionInfo t where t.startedBy=:user or t.serviceId.madeBy=:user")
    List<TransactionInfo> findAllUserTransactions(@Param("user") UserTable user, Pageable page);


    //esraaaa

    //nourrr

    //nouurr
}
