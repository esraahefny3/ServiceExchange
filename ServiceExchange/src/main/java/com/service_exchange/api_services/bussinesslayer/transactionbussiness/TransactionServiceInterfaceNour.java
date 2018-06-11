package com.service_exchange.api_services.bussinesslayer.transactionbussiness;

import com.service_exchange.api_services.dao.transaction.nour.TransactionDtoNour;

public interface TransactionServiceInterfaceNour {
    TransactionDtoNour makeTransactionOnService(TransactionDtoNour transactionDto);

    TransactionDtoNour completeTransaction(TransactionDtoNour transactionDto);

    TransactionDtoNour approveCompletedTransaction(TransactionDtoNour transactionDto);

    TransactionDtoNour rejectCompletedTransaction(TransactionDtoNour transactionDto);
}
