package com.service_exchange.api_services.bussinesslayer.transactionbussiness;
import com.service_exchange.api_services.dao.transaction.TransactionDto;

import java.util.List;

public interface TransactionServiceInterface {


    TransactionDto userAcceptTransaction(TransactionDto transactionDto);
    TransactionDto userApproveAcceptedTransaction(TransactionDto transactionDto);
    boolean userRejectTransaction(TransactionDto transactionDto);
    List<TransactionDto> getAllUserTransactions(Integer UserId, Integer pageNum);
}
