package com.service_exchange.api_services.bussinesslayer.transactionbussiness;

import com.service_exchange.api_services.bussinessdaodelegates.transaction.TransactionDelegateInterface;
import com.service_exchange.api_services.bussinessdaodelegates.transaction.transaction2.TransactionDelegateInterfaceNour;
import com.service_exchange.api_services.dao.service.ServiceData;
import com.service_exchange.api_services.dao.transaction.nour.TransactionDaoInterfaceNour;
import com.service_exchange.api_services.dao.transaction.nour.TransactionDtoNour;
import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.Service;
import com.service_exchange.entities.TransactionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionServiceNour implements TransactionServiceInterfaceNour {

    @Autowired
    TransactionDelegateInterfaceNour delegate;
    @Autowired
    TransactionDelegateInterface transactionDelegate;
    @Autowired
    TransactionDaoInterfaceNour transactionDao;
    @Autowired
    UserDataInterFace userDataInterFace;
    @Autowired
    ServiceData serviceData;

    @Override
    public TransactionDtoNour makeTransactionOnService(TransactionDtoNour transactionDto) {
        if ((delegate.checkIfUserExists(transactionDto.getStartedByUser()) != null) &&
                (delegate.checkIfServiceExists(transactionDto.getServiceId()) != null)) {
            if (serviceData.findById(transactionDto.getServiceId()).isPresent()) {
                Service service = serviceData.findById(transactionDto.getServiceId()).get();

                Integer userId = transactionDto.getStartedByUser();
                TransactionInfo transactionInfo = AppFactory.mapToEntity(transactionDto, TransactionInfo.class);
                transactionInfo.setType(service.getType());
                transactionInfo.setState(transactionInfo.PENDING_STATE);
                transactionInfo.setStartDate(new Date());

                transactionDao.save(transactionInfo);
                transactionInfo.setStartedBy(userDataInterFace.findById(userId).get());
                transactionDto = AppFactory.mapToDto(transactionInfo, TransactionDtoNour.class);
                transactionDto.setStartedByUser(userId);

                System.out.println(transactionDao.findByServiceId(service));

                return transactionDto;
            }
        }

        return null;
    }

    @Override
    public TransactionDtoNour completeTransaction(TransactionDtoNour transactionDto) {

//        TransactionInfo transactionInfo = transactionDelegate.checkIfTransactionExist(transactionDto.getId());
//        if (transactionInfo != null) {
//            transactionInfo.setState(TransactionInfo.COMPLETED_STATE);
//        }
//
        return null;
    }
}
