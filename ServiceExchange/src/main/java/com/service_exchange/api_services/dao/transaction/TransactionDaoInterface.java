/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.transaction;

import com.service_exchange.entities.Service;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author esraa
 */
@Repository
public interface TransactionDaoInterface extends PagingAndSortingRepository<TransactionInfo, Integer> {

    ////////////////////////////Esraa////////////////////////////

    @Query("select t from TransactionInfo t where t.startedBy=:user or t.serviceId.madeBy=:user")
    List<TransactionInfo> findAllUserTransactions(@Param("user") UserTable user, Pageable page);

    @Query("select t from TransactionInfo t where t.serviceId=:service and t.state=:state ")
    List<TransactionInfo> findAllUserTransactionsOnServiceWithState(@Param("service") Service service, @Param("state") String state);

    @Transactional
    @Modifying
    @Query("update  TransactionInfo t set t.state=:newState where t.serviceId=:service and t.state=:oldState ")
    int changeAllUserTransactionsStateOnServiceWithState(@Param("service") Service service, @Param("oldState") String oldState, @Param("newState") String newState);

    ////////////////////////////Esraa////////////////////////////


    //mubarak//
    Long countAllByIdIsNotNull();

    Long countAllByStateEquals(String state);
    //mubarak//


    ////////////////////////////Nouran////////////////////////////

    List<TransactionInfo> findByServiceId(Service service);

    @Query("select t from TransactionInfo t where (t.startedBy= :user or t.serviceId.madeBy= :user) and (t.state= :state)")
    List<TransactionInfo> findUserTransactionsByState(@Param("user") UserTable user, @Param("state") String state, Pageable page);

    @Query("select t from TransactionInfo t where (t.startedBy= :user) and " +
            "(t.state= 'accepted' or" +
            " t.state= 'pending' or " +
            "t.state= 'on_progress' or " +
            "t.state= 'postpone' or " +
            "t.state= 'completed')")
    List<TransactionInfo> findUserUnavailableTransactions(@Param("user") UserTable user);

    @Query("select t from TransactionInfo t where (t.serviceId= :service) and t.state= 'on_progress' ")
    List<TransactionInfo> findOnProgressTransactionsOnService(@Param("service") Service service);

    ////////////////////////////Nouran////////////////////////////

}
