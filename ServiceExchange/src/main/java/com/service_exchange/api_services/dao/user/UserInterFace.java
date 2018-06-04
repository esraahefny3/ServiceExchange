/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user;

import com.service_exchange.entities.Education;
import com.service_exchange.entities.UserTable;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Altysh
 */
public interface UserInterFace {

    @Nullable
    UserTable getUser(Integer userId);

    @Nullable
    UserTable getUserByEmail(String email);

    @Nullable
    UserTable createUser(UserTable user);

    void refresh(UserTable user);

    @Nullable
    UserTable updateUser(UserTable user);

    Boolean checkEmailAvalible(Integer email);

    Page<UserTable> getAllUser(int start);

    Page<UserTable> scerchUserByName(String name, int start);

    Boolean changePic(String url, Integer userId);

    Boolean addEductaion(Education education, Integer userId);

    Boolean removeEductaion(Education education, Integer userId);

    @Nullable
    Collection<Education> getEducation(Integer userId);

    List getUserComplaints(Integer userId);

}
