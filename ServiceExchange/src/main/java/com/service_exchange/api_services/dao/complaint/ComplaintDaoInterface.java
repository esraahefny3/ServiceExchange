/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.complaint;

import com.service_exchange.entities.Complaint;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author esraa
 */
@Repository
public interface ComplaintDaoInterface extends  PagingAndSortingRepository<Complaint,Integer>{

    List<Complaint>findComplaintByTransactionId(Integer transactionId);
}
