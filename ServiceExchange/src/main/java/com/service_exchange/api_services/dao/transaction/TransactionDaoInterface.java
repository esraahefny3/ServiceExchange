/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.transaction;

//esraaaa
import com.service_exchange.entities.TransactionInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
//esraaaa


//nourrr

//nouurr

/**
 *
 * @author esraa
 */
@Repository
public interface TransactionDaoInterface extends  PagingAndSortingRepository<TransactionInfo,Integer>{

    //esraaaa
//    @Query("select t from TransactionInfo t where t.startedBy=:user or t.serviceId.madeBy=:user")
//    List<TransactionInfo> findAllUserTransactions(@Param("user") UserTable user, Pageable page);


    //esraaaa

    //nourrr

    //nouurr
}
