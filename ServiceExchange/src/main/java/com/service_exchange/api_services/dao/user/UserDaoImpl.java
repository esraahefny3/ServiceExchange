/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user;

import com.service_exchange.api_services.dao.challanges.ChallangeDao;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.Education;
import com.service_exchange.entities.Review;
import com.service_exchange.entities.Service;
import com.service_exchange.entities.Skill;
import com.service_exchange.entities.TransactionInfo;

import com.service_exchange.entities.UserTable;
import com.service_exchange.utal.UnoptimizedDeepCopy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Altysh
 */
@Component
public class UserDaoImpl implements UserInterFace {

    @Autowired
    private UserDataInterFace dataInterface;

    @Autowired
    private ChallangeDao challangeDao;
    
    private int pageSize = 20;

    private UserTable getUser(Integer userId) {
        Optional<UserTable> user = dataInterface.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    private <T> List<T> convertList(Collection<T> coll) {
        List list;
        if (coll instanceof List) {
            list = (List) coll;
        } else {
            list = new ArrayList(coll);
        }
        return list;
    }

    @Override
    public UserTable createUser(UserTable user) {
        UserTable muser = dataInterface.save(user);
        
        return muser;
    }

    @Override
    public UserTable updateUser(UserTable user) {
        return (UserTable) UnoptimizedDeepCopy.copy(dataInterface.save(user));
    }

    @Override
    public Boolean checkEmailAvalible(Integer email) {
        return dataInterface.findById(email).isPresent();
    }

    @Override
    @Transactional
    public Page<UserTable> getAllUser(int start) {

        Page<UserTable> us = dataInterface.findAll(PageRequest.of(start,pageSize));

        return us;
    }

    public List<UserTable> getAllUser() {
        List<UserTable> list = new LinkedList<>();
        dataInterface.findAll().forEach((t) -> {
            list.add(t);
            // System.out.println(t.getComplaintCollection());
        });
        return list;
    }

    @Override
    public Page<UserTable> scerchUserByName(String name, int start) {
        return (Page<UserTable>) UnoptimizedDeepCopy.copy(dataInterface.findByNameContains(name, new PageRequest(start, start + 20)));

    }

   
    
    public Boolean addChallangeToUser(Integer chId, Integer userId) {

        Optional<UserTable> user = dataInterface.findById(userId);

        if (user.isPresent()) {
            boolean state = user.get().addChallange(chId);
            if (state) {
                dataInterface.save(user.get());

            }
            return state;
        }

        return false;
    }

   
    public Boolean removeChallangeToUser(Integer chId, Integer userId) {
        Optional<UserTable> user = dataInterface.findById(userId);
        if (user.isPresent()) {
            boolean state = user.get().removeChallange(chId);
            if (state) {
                dataInterface.save(user.get());

            }
            return state;
        }
        return false;
    }

  
    public Boolean addSkill(Skill skill, Integer userId) {
        Optional<UserTable> user = dataInterface.findById(userId);
        if (user.isPresent()) {
            boolean state = user.get().addSkill(skill);
            if (state) {
                dataInterface.save(user.get());

            }
            return state;
        }
        return false;
    }

    public Boolean deleteSkill(Skill skill, Integer userId) {
        Optional<UserTable> user = dataInterface.findById(userId);
        if (user.isPresent()) {
            boolean state = user.get().addSkill(skill);
            if (state) {
                dataInterface.delete(user.get());

            }
            return state;
        }
        return false;
    }

    @Override
    public Boolean changePic(String url, Integer userId) {
        UserTable user = getUser(userId);
        if (user != null) {
            user.setImage(url);
            return true;
        }
        return false;
    }

    @Override
    public Boolean addEductaion(Education education, Integer userId) {
        UserTable user = getUser(userId);
        if (user != null) {
            user.addEducation(education);
            return true;
        }
        return false;
    }

    @Override
    public Boolean removeEductaion(Education education, Integer userId) {
        UserTable user = getUser(userId);
        if (user != null) {
            user.removeEducation(education);
            return true;
        }
        return false;
    }

    @Override
    public Collection<Education> getEducation(Integer userId) {
        UserTable user = getUser(userId);
        if (user != null) {
            return user.getEducationCollection();
        }
        return null;
    }

    
    public List<Skill> getUserSkill(Integer userId) {
        UserTable user = getUser(userId);
        if (user != null) {
            return convertList(user.getSkillCollection());
        }
        return null;
    }

    @Override
    public List<Complaint> getUserComplaints(Integer userId) {
        UserTable user = getUser(userId);
        if (user != null) {
            return convertList(user.getComplaintCollection());
        }
        return null;
    }

    
    public List<Review> getUserReviews(Integer userId) {
        UserTable user = getUser(userId);
        if (user != null) {
            return convertList(user.getReviewCollection());
        }
        return null;
    }

   
    public Boolean addReviewToUser(Integer userId, Review review) {
        UserTable user = getUser(userId);
        if (user != null) {
            return user.addReview(review);
        }
        return null;
    }

    
    public List<Service> getUserServices(Integer userId) {
        UserTable user = getUser(userId);
        if (user != null) {
            return convertList(user.getServiceCollection());
        }
        return null;
    }

   
    public Boolean addServiceToUser(Integer userId, Service service) {
        UserTable user = getUser(userId);
        if (user != null) {
            return user.addService(service);
        }
        return null;
    }

 
    public Boolean removeServiceForUser(Integer userId, Service service, Boolean forced) {
        UserTable user = getUser(userId);
        if (user != null) {
            return user.removeService(service);
        }
        return null;
    }

   

}
