/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userReview

import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.entities.Review
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

/*

  @author Altysh
  Created on May 29, 2018
*/
@Component
class UserReivewImpl : UserReviewInterFace {
    @Autowired
    lateinit var userInterface: UserInterFace;

    override fun addReviewToUser(userId: Int?, review: Review?): Boolean? {
        return userInterface.getUser(userId).addReview(review)
    }


    override fun getUserReviews(userId: Int?): MutableList<Review>? =
            userInterface.getUser(userId)?.getReviewCollection()?.stream()?.collect(Collectors.toList())


}