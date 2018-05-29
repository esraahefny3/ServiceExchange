/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userReview;

import com.service_exchange.entities.Review;

import java.util.List;

/**
 *
 * @author Altysh
 */
public interface UserReviewInterFace {
    List<Review> getUserReviews(Integer userId);

    Boolean addReviewToUser(Integer userId,Review review);
}
