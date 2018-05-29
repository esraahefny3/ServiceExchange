/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user;

import com.service_exchange.entities.Challenge;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.Education;
import com.service_exchange.entities.Review;
import com.service_exchange.entities.Service;
import com.service_exchange.entities.Skill;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author Altysh
 */
public interface UserInterFace {

    UserTable getUser(Integer userId);

    UserTable createUser(UserTable user);

    UserTable updateUser(UserTable user);

    Boolean checkEmailAvalible(Integer email);

    Page<UserTable> getAllUser(int start);

    Page<UserTable> scerchUserByName(String name, int start);

    Boolean changePic(String url, Integer userId);

    Boolean addEductaion(Education education, Integer userId);

    Boolean removeEductaion(Education education, Integer userId);

    Collection<Education> getEducation(Integer userId);

    List<Complaint> getUserComplaints(Integer userId);

}
