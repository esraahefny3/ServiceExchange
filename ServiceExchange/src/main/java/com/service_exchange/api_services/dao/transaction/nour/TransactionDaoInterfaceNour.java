package com.service_exchange.api_services.dao.transaction.nour;


import com.service_exchange.entities.Service;
import com.service_exchange.entities.TransactionInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDaoInterfaceNour extends PagingAndSortingRepository<TransactionInfo, Integer> {
    public List<TransactionInfo> findByServiceId(Service service);
}
