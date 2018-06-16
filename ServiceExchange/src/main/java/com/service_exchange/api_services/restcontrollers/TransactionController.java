package com.service_exchange.api_services.restcontrollers;

import com.service_exchange.api_services.bussinesslayer.transactionbussiness.TransactionServiceInterface;
import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.service_exchange.api_services.dao.transaction.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    ////////////////////////////Esraa////////////////////////////

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

        if (transactionDto != null && transactionDto.getId() != null) {
            return transactionServiceImpl.userApproveAcceptedTransaction(transactionDto);
        }
        return null;
    }

    @RequestMapping(value = "/userAcceptedThenAcceptTransaction", method = RequestMethod.POST)
    public TransactionDto userAcceptedThenApproveTransaction(@RequestBody TransactionDto transactionDto) {

        if (transactionDto != null && transactionDto.getId() != null && transactionDto.getPrice() != null && transactionDto.getDuration() != null) {
            return transactionServiceImpl.userAcceptedThenApproveTransaction(transactionDto);
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

    ////////////////////////////Esraa////////////////////////////

    ////////////////////////////Nouran////////////////////////////

    @Autowired
    TransactionServiceInterface transactionServiceInterface;


    @RequestMapping(value = "/makeTransactionOnService", method = RequestMethod.POST)
    public TransactionDto makeTransactionOnService(@RequestBody TransactionDto transactionDto) {
        if (transactionDto != null) {
            return transactionServiceInterface.makeTransactionOnService(transactionDto);
        } else {
            return null;
        }
    }


    @RequestMapping(value = "/completeTransaction", method = RequestMethod.POST)
    public TransactionDto completeTransaction(@RequestBody TransactionDto transactionDto) {
        if (transactionDto != null) {
            return transactionServiceInterface.completeTransaction(transactionDto);
        } else {
            return null;
        }
    }


//    @RequestMapping(value = "/approveCompletedTransaction", method = RequestMethod.POST)
//    public TransactionDto approveCompletedTransaction(@RequestBody TransactionDto transactionDto) {
//        if (transactionDto != null) {
//            return transactionServiceInterface.approveCompletedTransaction(transactionDto);
//        } else {
//            return null;
//        }
//    }


    @RequestMapping(value = "/rejectCompletedTransaction", method = RequestMethod.POST)
    public TransactionDto rejectCompletedTransaction(@RequestBody TransactionDto transactionDto) {
        if (transactionDto != null) {
            return transactionServiceInterface.rejectCompletedTransaction(transactionDto);
        } else {
            return null;
        }
    }


    @RequestMapping(value = "/getUserActiveTransactions/{userId}/{pageNum}", method = RequestMethod.GET)
    public List<TransactionDto> getUserActiveTransactions(@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum) {

        if (userId != null && pageNum != null) {
            return transactionServiceImpl.getUserActiveTransactions(userId, pageNum);
        }
        return null;
    }


    @RequestMapping(value = "/getUserCompletedTransactions/{userId}/{pageNum}", method = RequestMethod.GET)
    public List<TransactionDto> getUserCompletedTransactions(@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum) {

        if (userId != null && pageNum != null) {
            return transactionServiceImpl.getUserCompletedTransactions(userId, pageNum);
        }
        return null;
    }

    @RequestMapping(value = "/getUserCompletedAndApprovedTransactions/{userId}/{pageNum}", method = RequestMethod.GET)
    public List<TransactionDto> getUserCompletedAndApprovedTransactions(@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum) {

        if (userId != null && pageNum != null) {
            return transactionServiceImpl.getUserCompletedAndApprovedTransactions(userId, pageNum);
        }
        return null;
    }


    @RequestMapping(value = "/approveCompletedTransaction", method = RequestMethod.POST)
    public TransactionDto approveCompletedTransaction(@RequestBody Map<String, Object> mapData) {
        TransactionDto transactionDto = (TransactionDto) mapData.get("transaction");
        ReviewDTO reviewDTO = (ReviewDTO) mapData.get("review");
        if (transactionDto != null && reviewDTO != null) {

            return transactionServiceInterface.approveCompletedTransaction(transactionDto);
        } else {
            return null;
        }
    }

    ////////////////////////////Nouran////////////////////////////

}
