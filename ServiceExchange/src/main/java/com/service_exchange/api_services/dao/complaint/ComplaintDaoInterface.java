/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.complaint;

import com.service_exchange.entities.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 *
 * @author esraa
 */
@Repository
public interface ComplaintDaoInterface extends  PagingAndSortingRepository<Complaint,Integer>{

    List<Complaint> findComplaintByTransactionId_Id(Integer transactionId);

    Page<Complaint> findAllByStateEqualsOrderByDate(String state, Pageable page);

    Page<Complaint> findAllByReviewedByNullOrderByDate(Pageable page);

    Page<Complaint> findAllByDateIsNearOrderByDate(Date date, Pageable page);

    Long countAllByIdIsNotNull();

    Long countAllByStateEquals(String state);



}
