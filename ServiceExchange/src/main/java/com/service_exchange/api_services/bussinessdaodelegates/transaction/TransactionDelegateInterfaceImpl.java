package com.service_exchange.api_services.bussinessdaodelegates.transaction;

//ersaaa
import com.service_exchange.api_services.dao.transaction.TransactionDaoInterface;
import com.service_exchange.entities.TransactionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

//esraaa

//esraaa

//nour

//nourr

@Component
public class TransactionDelegateInterfaceImpl implements TransactionDelegateInterface {

    ////////////////////////////Nouran////////////////////////////

    ////////////////////////////Nouran////////////////////////////


    ////////////////////////////Esraa////////////////////////////
    @Autowired
   private TransactionDaoInterface transactionDaoInterfaceImpl;
    @Override
    public TransactionInfo checkIfTransactionExist(Integer transactionId) {
        Optional<TransactionInfo> transactionInfoOptional=transactionDaoInterfaceImpl.findById(transactionId);
        if(transactionInfoOptional.isPresent()==true)
        {
            return transactionInfoOptional.get();
        }
        else{
            return  null;
        }
    }


    ////////////////////////////Esraa////////////////////////////
}
