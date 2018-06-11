package com.service_exchange.api_services.bussinessdaodelegates.transaction;

import com.service_exchange.api_services.dao.transaction.TransactionDto;
import com.service_exchange.entities.Service;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;

import java.util.List;

public interface TransactionDelegateInterface {

    ////////////////////////////Esraa////////////////////////////

    TransactionInfo checkIfTransactionExist(Integer transactionId);

    TransactionDto saveTransaction(TransactionInfo transactionInfo);

    List<TransactionDto> getAllUserTransactions(UserTable user, Integer pageNumbe);

    List<TransactionInfo> getAllUserAcceptedTransactionsOnService(Service service);

    int postponeAllOtherUserPindingTransactionOnService(Service service);

    int rejectAcceptedTransactionOnService(Service service);

    ////////////////////////////Esraa////////////////////////////


    ////////////////////////////Nouran////////////////////////////

    UserTable checkIfUserExists(Integer userId);

    Service checkIfServiceExists(Integer serviceId);

    boolean completeTransaction(TransactionInfo transactionInfo);

    boolean approveCompletedTransaction(TransactionInfo transactionInfo);

    boolean rejectCompletedTransaction(TransactionInfo transactionInfo);

    ////////////////////////////Nouran////////////////////////////
}
