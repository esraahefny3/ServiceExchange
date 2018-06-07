/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.message;

import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.Message;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *
 * @author esraa
 */
@Repository
public interface MessageInterface extends PagingAndSortingRepository<Message,Integer>, CustomMessageInterface{

    @Transactional
    @Modifying
    @Query("update Message m set m.isSeen=:isSeen,m.seenDate=:seenDate where m.isSeen=:notSeenState and m.transactionId=:transactionId and m.senderId <>:senderId")
    public int updateAllTransactionMessagesSeenState(@Param("notSeenState")Short notSeenState, @Param("isSeen") Short isSeen, @Param("seenDate")Date seenDate,
                                                         @Param("transactionId")TransactionInfo transactionId, @Param("senderId")UserTable senderId);

    @Transactional
    @Modifying
    @Query("update Message m set m.isSeen=:isSeen,m.seenDate=:seenDate where m.isSeen=:notSeenState and m.complaintId=:complaintId and m.senderId <>:senderId")
    public int updateAllComplaintMessagesSeenState(@Param("notSeenState")Short notSeenState, @Param("isSeen") Short isSeen, @Param("seenDate")Date seenDate,
                                                       @Param("complaintId")Complaint complaintId, @Param("senderId")UserTable senderId);

}
