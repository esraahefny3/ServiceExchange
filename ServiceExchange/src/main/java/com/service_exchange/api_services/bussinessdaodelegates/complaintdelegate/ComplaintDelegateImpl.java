/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.complaintdelegate;

import com.service_exchange.api_services.dao.admin.AdminDataInterface;
import com.service_exchange.api_services.dao.complaint.ComplaintDaoInterface;
import com.service_exchange.api_services.dao.dto.ComplaintDto;
import com.service_exchange.api_services.dao.transaction.TransactionDaoInterface;
import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.AdminTable;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author esraa
 */
@Component
public class ComplaintDelegateImpl implements ComplaintDelegateInterface{

    @Autowired
    private ComplaintDaoInterface complaintDaoInterfaceImpl;
    
    @Autowired
    private UserDataInterFace userDataInterFaceImpl;

    @Autowired 
    private TransactionDaoInterface transactionDaoInterfaceImpl;
    
    @Autowired
    private AdminDataInterface adminDataInterfaceImpl;
//
//    @Autowired
//    private AdminDataInterface adminDataInterface;
//
    @Override
    public ComplaintDto saveComplaint(Complaint complaint) {
        
        try{
            Complaint complaintTemp= complaintDaoInterfaceImpl.save(complaint);
            ComplaintDto complaintDto= AppFactory.mapToDto(complaintTemp, ComplaintDto.class);
            complaintDto.setDate(complaintTemp.getDate().getTime());
            return complaintDto;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    
    }

    @Override
    public UserTable checkUserExistById(Integer userId)
    {
        try {
            return userDataInterFaceImpl.findById(userId).get();
        }catch (Exception e)
        {
            return  null;
        }

    }

    @Override
    public Complaint checkCoplaintExistById(Integer complaintId) {
        Optional<Complaint> complaintOptional=complaintDaoInterfaceImpl.findById(complaintId);
        if(complaintOptional.isPresent()==true)
        {
            return complaintOptional.get();
        }
        else
        {
            return null;
        }
    }

    @Override
    public TransactionInfo checkTransactionExistById(Integer transactionId) {
        try {
            return transactionDaoInterfaceImpl.findById(transactionId).get();
        }catch (Exception e)
        {
            return  null;
        }
    }

    @Override
    public Complaint checkComplaintExsistById(Integer complaintId) {
        try {
            return complaintDaoInterfaceImpl.findById(complaintId).get();
        }
        catch (Exception e)
        {
            return  null;
        }
    }

//here
    @Override
    public AdminTable checkAdminExistById(String adminEmail) {
        try{
             return adminDataInterfaceImpl.findAdminTableByEmail(adminEmail);
        }catch (Exception e)
        {
            return  null;
        }
    }

    @Override
    public boolean checkUserMadeComplaintIntTransaction(Integer userId, Integer transactionId) {

      boolean retVal=true;
     try {
         List<Complaint> complaintList = complaintDaoInterfaceImpl.findComplaintByTransactionId(transactionId);
         for (Complaint complaint : complaintList) {
             if (complaint.getUserId().getId() == userId) {
                 retVal = false;
             }
         }
     }catch (Exception e)
     {
         return  false;
     }
        return retVal;
    }

}
