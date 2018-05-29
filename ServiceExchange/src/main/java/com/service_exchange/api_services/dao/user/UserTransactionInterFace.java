/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user;

import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import java.util.List;

/**
 *
 * @author Altysh
 */
public interface UserTransactionInterFace {
    List<TransactionInfo> getSuccsfullTransaction(UserTable user);
    Long getWorkingDuration();

    Long getWoringDurtation(UserTable user, long start, long end);
}
