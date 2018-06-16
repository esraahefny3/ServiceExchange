package com.service_exchange.api_services.bussinesslayer.reviewbusiness;

import com.service_exchange.api_services.bussinessdaodelegates.review.ReviewDelegateInterface;
import com.service_exchange.api_services.dao.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewService implements ReviewServiceInterface {

    @Autowired
    ReviewDelegateInterface delegateInterface;

    @Override
    public boolean submitReviewOnTransaction(ReviewDTO reviewDTO, Integer transactionId) {
        if (reviewDTO != null && transactionId != null) {
            return delegateInterface.submitReviewOnTransaction(reviewDTO, transactionId);
        }
        return false;
    }

}
