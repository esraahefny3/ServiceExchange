package com.service_exchange.api_services.bussinessdaodelegates.transaction.transaction2;

import com.service_exchange.api_services.dao.service.ServiceData;
import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.entities.Service;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionDelegateInterfaceNourImpl implements TransactionDelegateInterfaceNour {

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
}
