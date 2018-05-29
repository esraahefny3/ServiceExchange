/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userTranaction;

import com.service_exchange.entities.TransactionInfo;

import java.util.List;

/**
 *
 * @author Altysh
 */
public interface UserTransactionInterFace {
    List<TransactionInfo> getSuccsfullTransaction(Integer userId);

    Long getWorkingDuration(Integer userId);

    Long getWoringDurtation(Integer user, long start, long end);
}
