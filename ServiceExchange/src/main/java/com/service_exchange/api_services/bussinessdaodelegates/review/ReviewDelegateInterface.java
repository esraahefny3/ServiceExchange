package com.service_exchange.api_services.bussinessdaodelegates.review;

import com.service_exchange.api_services.dao.dto.ReviewDTO;

public interface ReviewDelegateInterface {

    boolean submitReviewOnTransaction(ReviewDTO reviewDTO, Integer transactionId);

}
