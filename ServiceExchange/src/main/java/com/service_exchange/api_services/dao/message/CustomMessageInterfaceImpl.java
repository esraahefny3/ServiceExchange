/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.message;

import com.service_exchange.entities.Message;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author esraa
 */
public class CustomMessageInterfaceImpl implements CustomMessageInterface{

    @PersistenceContext
    EntityManager entityManager;
 
    private int limit=20;
    @Override
    public List<Message> findAllPrivateChatMessages(Integer user1Id, Integer user2Id, Integer pageNum,String tableName) {
   
     Query query = entityManager.createNativeQuery("select e.* from ? " +
                "where (sender_id=? and receiver_id=?) or(sender_id=? and receiver_id=?) ", Message.class);
        query.setParameter(1, tableName );
        query.setParameter(2, user1Id);
        query.setParameter(3, user2Id);
        query.setParameter(4, user2Id);
        query.setParameter(5, user1Id);
        
        //Paginering
        query.setFirstResult(pageNum);
        query.setMaxResults(limit);
        
        final List<Message> resultList = query.getResultList();
//        List<Message> somethingList = new ArrayList();
//        resultList.forEach(object -> somethingList.add(object));
//        return somethingList;
        return resultList;
    }
    
}
