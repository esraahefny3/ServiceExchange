package com.service_exchange.api_services.dao.review;

import com.service_exchange.entities.Review;
import com.service_exchange.entities.TransactionInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReviewDaoInterface extends PagingAndSortingRepository<Review, Integer> {
    List<Review> findByTransactionId(TransactionInfo transaction);
}
