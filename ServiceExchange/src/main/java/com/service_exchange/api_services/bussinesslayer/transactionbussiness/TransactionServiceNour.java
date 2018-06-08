package com.service_exchange.api_services.bussinesslayer.transactionbussiness;

import com.service_exchange.api_services.bussinessdaodelegates.transaction.transaction2.TransactionDelegateInterfaceNour;
import com.service_exchange.api_services.dao.service.ServiceData;
import com.service_exchange.api_services.dao.transaction.nour.TransactionDaoInterfaceNour;
import com.service_exchange.api_services.dao.transaction.nour.TransactionDtoNour;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.Service;
import com.service_exchange.entities.TransactionInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TransactionServiceNour implements TransactionServiceInterfaceNour {

    @Autowired
    TransactionDelegateInterfaceNour delegate;
    @Autowired
    TransactionDaoInterfaceNour transactionDao;
    @Autowired
    ServiceData serviceData;

    @Override
    public TransactionDtoNour makeTransactionOnService(TransactionDtoNour transactionDto) {
        if ((delegate.checkIfUserExists(transactionDto.getStartedByUser()) != null) && (delegate.checkIfServiceExists(transactionDto.getServiceId()) != null)) {
            if (serviceData.findById(transactionDto.getServiceId()).isPresent()) {
                Service service = serviceData.findById(transactionDto.getServiceId()).get();
                TransactionInfo transactionInfo = AppFactory.mapToEntity(transactionDto, TransactionInfo.class);
                transactionInfo.setType(service.getType());
                transactionInfo.setState(transactionInfo.PENDING_STATE);
                transactionInfo.setStartDate(new Date());
                transactionInfo.setPrice(service.getPrice());

                transactionDao.save(transactionInfo);
                transactionDto = AppFactory.mapToDto(transactionInfo, TransactionDtoNour.class);
                return transactionDto;
            }
        }
        return null;
    }
}
