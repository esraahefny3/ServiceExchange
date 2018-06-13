package com.service_exchange.api_services.bussinesslayer.transactionbussiness;

import com.service_exchange.api_services.dao.transaction.TransactionDto;

import java.util.List;

public interface TransactionServiceInterface {

    ////////////////////////////Esraa////////////////////////////

    TransactionDto userAcceptTransaction(TransactionDto transactionDto);

    TransactionDto userApproveAcceptedTransaction(TransactionDto transactionDto);

    boolean userRejectTransaction(TransactionDto transactionDto);

    List<TransactionDto> getAllUserTransactions(Integer UserId, Integer pageNum);

    ////////////////////////////Esraa////////////////////////////

    ////////////////////////////Nouran////////////////////////////

    TransactionDto makeTransactionOnService(TransactionDto transactionDto);

    TransactionDto completeTransaction(TransactionDto transactionDto);

    TransactionDto approveCompletedTransaction(TransactionDto transactionDto);

    TransactionDto rejectCompletedTransaction(TransactionDto transactionDto);

    List<TransactionDto> getUserActiveTransactions(Integer userId, Integer pageNum);

    List<TransactionDto> getUserCompletedTransactions(Integer userId, Integer pageNum);

    List<TransactionDto> getUserCompletedAndApprovedTransactions(Integer userId, Integer pageNum);

    ////////////////////////////Nouran////////////////////////////

}
