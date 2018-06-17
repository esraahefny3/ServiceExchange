package com.service_exchange.api_services.bussinesslayer.reviewbusiness;

import com.service_exchange.api_services.dao.dto.ReviewDTO;

public interface ReviewServiceInterface {

    boolean submitReviewOnTransaction(ReviewDTO reviewDTO, Integer transactionId);

}
