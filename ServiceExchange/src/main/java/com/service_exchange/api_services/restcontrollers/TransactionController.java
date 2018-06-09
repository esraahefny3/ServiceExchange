package com.service_exchange.api_services.restcontrollers;

import com.service_exchange.api_services.bussinesslayer.transactionbussiness.TransactionServiceInterface;
import com.service_exchange.api_services.dao.transaction.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionServiceInterface transactionServiceImpl;

//    TransactionDto userAcceptTransaction(TransactionDto transactionDto);
//    TransactionDto userApproveAcceptedTransaction(TransactionDto transactionDto);
//    boolean userRejectTransaction(TransactionDto transactionDto);
//    List<TransactionDto> getAllUserTransactions(Integer UserId, Integer pageNum);

    @RequestMapping(value = "/getAllUserTransactions/{userId}/{pageNum}", method = RequestMethod.GET)
    public List<TransactionDto> getAllUserTransactions(@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum) {

        if (userId != null && pageNum != null) {
            return transactionServiceImpl.getAllUserTransactions(userId,pageNum);
        }
        return null;
    }
    @RequestMapping(value = "/userAcceptTransaction", method = RequestMethod.POST)
    public TransactionDto userAcceptTransaction(@RequestBody TransactionDto transactionDto) {

        if (transactionDto != null&&transactionDto.getId()!=null&&transactionDto.getPrice()!=null&&transactionDto.getDuration()!=null) {
            return transactionServiceImpl.userAcceptTransaction(transactionDto);
        }
       return null;
    }

    @RequestMapping(value = "/userApproveAcceptedTransaction", method = RequestMethod.POST)
    public TransactionDto userApproveAcceptedTransaction(@RequestBody TransactionDto transactionDto) {

         if (transactionDto != null&&transactionDto.getId()!=null) {
            return transactionServiceImpl.userApproveAcceptedTransaction(transactionDto);
        }
        return null;
    }

    @RequestMapping(value = "/userRejectTransaction", method = RequestMethod.POST)
    public boolean userRejectTransaction(@RequestBody TransactionDto transactionDto) {

        if (transactionDto != null&&transactionDto.getId()!=null) {
            return transactionServiceImpl.userRejectTransaction(transactionDto);
        }
        return false;
    }
}
