package com.service_exchange.api_services.bussinessdaodelegates.transaction;

//ersaaa

import com.service_exchange.api_services.dao.service.ServiceData;
import com.service_exchange.api_services.dao.transaction.TransactionDaoInterface;
import com.service_exchange.api_services.dao.transaction.TransactionDto;
import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.Service;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionDelegateInterfaceImpl implements TransactionDelegateInterface {


    ////////////////////////////Esraa////////////////////////////

    private int size=20;
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

    @Override
    public TransactionDto saveTransaction(TransactionInfo transactionInfo) {
        try {
            if(transactionInfo!=null)
            {
                transactionInfo=transactionDaoInterfaceImpl.save(transactionInfo);
                return AppFactory.mapToDto(transactionInfo,TransactionDto.class);
            }
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TransactionDto> getAllUserTransactions(UserTable user,Integer pageNumber ) {

        try{
            List<TransactionInfo> transactionInfoList= transactionDaoInterfaceImpl.findAllUserTransactions(user,PageRequest.of(pageNumber,size));
            List<TransactionDto>transactionDtoList=new ArrayList<>();
            transactionInfoList.forEach(transactionInfo->transactionDtoList.add(AppFactory.mapToDto(transactionInfo,TransactionDto.class)));
            return transactionDtoList;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<TransactionInfo> getAllUserAcceptedTransactionsOnService(Service service) {
        try{
            return transactionDaoInterfaceImpl.findAllUserTransactionsOnServiceWithState(service,TransactionInfo.ACCEPTED_STATE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int postponeAllOtherUserPindingTransactionOnService(Service service) {
        try{
             return transactionDaoInterfaceImpl.changeAllUserTransactionsStateOnServiceWithState(service,TransactionInfo.PENDING_STATE,TransactionInfo.POSTPONED);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int rejectAcceptedTransactionOnService(Service service) {
        try{
            return transactionDaoInterfaceImpl.changeAllUserTransactionsStateOnServiceWithState(service,TransactionInfo.ACCEPTED_STATE,TransactionInfo.REJECTED_STATE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }


    ////////////////////////////Esraa////////////////////////////


    ////////////////////////////Nouran////////////////////////////

    @Autowired
    private UserDataInterFace userDataInterface;
    @Autowired
    private ServiceData serviceData;

    @Override
    public UserTable checkIfUserExists(Integer userId) {

        Optional<UserTable> user = userDataInterface.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @Override
    public Service checkIfServiceExists(Integer serviceId) {
        Optional<Service> service = serviceData.findById(serviceId);
        if (service.isPresent()) {
            return service.get();
        } else {
            return null;
        }
    }

    @Override
    public boolean completeTransaction(TransactionInfo transactionInfo) {
        try {
            transactionInfo.setState(TransactionInfo.COMPLETED_STATE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean approveCompletedTransaction(TransactionInfo transactionInfo) {
        try {
            transactionInfo.setState(TransactionInfo.COMPLETED_APPROVED_STATE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean rejectCompletedTransaction(TransactionInfo transactionInfo) {
        try {
            transactionInfo.setState(TransactionInfo.NOT_COMPLETED_STATE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    ////////////////////////////Nouran////////////////////////////
}
