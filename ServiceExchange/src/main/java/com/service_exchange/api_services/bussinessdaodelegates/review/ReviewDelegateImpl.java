package com.service_exchange.api_services.bussinessdaodelegates.review;

import com.service_exchange.api_services.bussinessdaodelegates.transaction.TransactionDelegateInterface;
import com.service_exchange.api_services.bussinessdaodelegates.user.UserDelegateInterface;
import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.service_exchange.api_services.dao.review.ReviewDaoInterface;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.Review;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ReviewDelegateImpl implements ReviewDelegateInterface {

    @Autowired
    ReviewDaoInterface reviewDaoInterface;

    @Autowired
    UserDelegateInterface userDelegateInterface;

    @Autowired
    TransactionDelegateInterface transactionDelegateInterface;

    @Override
    public boolean submitReviewOnTransaction(ReviewDTO reviewDTO, Integer transactionId) {
        UserTable user = userDelegateInterface.getUserById(reviewDTO.getMadeBy());
        TransactionInfo transaction = transactionDelegateInterface.checkIfTransactionExist(transactionId);

        List reviewsOnTransaction = reviewDaoInterface.findByTransactionId(transaction);

        boolean reviewIsValid = true;
        for (Object aReviewsOnTransaction : reviewsOnTransaction) {
            Review review = (Review) aReviewsOnTransaction;
            if (review.getMadeBy().getId().equals(user.getId())) {
                reviewIsValid = false;
            }
        }

        if (user != null && transaction != null && reviewIsValid) {
            reviewDTO.setTransactionId(transactionId);
            Review review = AppFactory.mapToEntity(reviewDTO, Review.class);
            review.setMadeBy(user);
            review.setDate(new Date());
            review.setIsDeleted((short) 0);
            reviewDaoInterface.save(review);
            return true;
        }
        return false;
    }

}
