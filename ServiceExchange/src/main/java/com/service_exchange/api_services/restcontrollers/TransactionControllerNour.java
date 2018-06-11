package com.service_exchange.api_services.restcontrollers;

import com.service_exchange.api_services.bussinesslayer.transactionbussiness.TransactionServiceInterfaceNour;
import com.service_exchange.api_services.dao.transaction.nour.TransactionDtoNour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionControllerNour {

    @Autowired
    TransactionServiceInterfaceNour transactionServiceInterface;


    @RequestMapping(value = "/makeTransactionOnService", method = RequestMethod.POST)
    public TransactionDtoNour makeTransactionOnService(@RequestBody TransactionDtoNour transactionDto) {
        if (transactionDto != null) {
            return transactionServiceInterface.makeTransactionOnService(transactionDto);
        } else {
            return null;
        }
    }


    @RequestMapping(value = "/completeTransaction", method = RequestMethod.POST)
    public TransactionDtoNour completeTransaction(@RequestBody TransactionDtoNour transactionDto) {
        if (transactionDto != null) {
            return transactionServiceInterface.completeTransaction(transactionDto);
        } else {
            return null;
        }
    }

}
