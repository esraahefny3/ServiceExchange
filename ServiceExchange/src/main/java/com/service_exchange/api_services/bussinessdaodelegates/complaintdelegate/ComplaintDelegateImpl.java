/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.complaintdelegate;

import com.service_exchange.api_services.dao.admin.AdminDaoInterface;
import com.service_exchange.api_services.dao.complaint.ComplaintDaoInterface;
import com.service_exchange.api_services.dao.complaint.complaintdtos.ComplaintDto;
import com.service_exchange.api_services.dao.transaction.TransactionDaoInterface;
import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.AdminTable;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author esraa
 */
public class ComplaintDelegateImpl implements ComplaintDelegateInterface{

    @Autowired
    private ComplaintDaoInterface complaintDaoInterfaceImpl;
    
    @Autowired
    private UserDataInterFace userDataInterFaceImpl;

    @Autowired 
    private TransactionDaoInterface transactionDaoInterfaceImpl;
    
    @Autowired
    private AdminDaoInterface adminDataInterfaceImpl;
    
    @Override
    public ComplaintDto saveComplaint(Complaint complaint) {
        
        try{
            Complaint complaintTemp= complaintDaoInterfaceImpl.save(complaint);
            return AppFactory.mapToDto(complaintTemp, ComplaintDto.class);
        }
        catch(Exception e)
        {
            return null;
        }
    
    }

    @Override
    public UserTable checkUserExistById(Integer userId) {
        return userDataInterFaceImpl.findById(userId).get();
    }

    @Override
    public TransactionInfo checkTransactionExistById(Integer transactionId) {
        return transactionDaoInterfaceImpl.findById(transactionId).get();
    }

    @Override
    public Complaint checkComplaintExsistById(Integer complaintId) {
        return complaintDaoInterfaceImpl.findById(complaintId).get();
    }

    @Override
    public AdminTable checkAdminExistById(Integer adminId) {
       return adminDataInterfaceImpl.findById(adminId).get();
    }
    
}
