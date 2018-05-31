/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.message;

import com.service_exchange.entities.Message;
import java.util.List;

/**
 *
 * @author esraa
 */
public interface CustomMessageInterface {
    public List<Message> findAllPrivateChatMessages(Integer user1Id,Integer user2Id,Integer pageNum,String tableName);
}
