/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer.complaint;

import com.service_exchange.api_services.bussinessdaodelegates.complaintdelegate.ComplaintDelegateInterface;
import com.service_exchange.api_services.dao.dto.ComplaintDto;
import com.service_exchange.entities.AdminTable;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * @author esraa
 */
@Component
  public class CompalintService  implements ComplaintServiceInterface{

      @Autowired
      private ComplaintDelegateInterface complaintDelegateInterfaceImpl;
    @Override
    public ComplaintDto userMakeComplaintOnTransaction(Integer userId, Integer transactionId, Complaint complaint) {
    
            UserTable user=complaintDelegateInterfaceImpl.checkUserExistById(userId);
            TransactionInfo transactionInfo=complaintDelegateInterfaceImpl.checkTransactionExistById(transactionId);
            if(user!=null&&transactionInfo!=null)
            {
                if(complaintDelegateInterfaceImpl.checkUserMadeComplaintIntTransaction(userId,transactionId)==false)//user didnt make complaintt in this transaction before
              {
                  complaint.setUserId(user);
                  complaint.setTransactionId(transactionInfo);
                  complaint.setState(Complaint.NOT_REVIEWED_STATE);
                  complaint.setDate(new Date());
                  return complaintDelegateInterfaceImpl.saveComplaint(complaint);
              }
            }
            return null;
    }

    @Override
    public ComplaintDto userMakeComplaintGeneral(Integer userId, Complaint complaint) {
        UserTable user=complaintDelegateInterfaceImpl.checkUserExistById(userId);
        if(user!=null)
        {
            System.out.println("hna2");
            complaint.setUserId(user);
            complaint.setState(Complaint.NOT_REVIEWED_STATE);
            complaint.setDate(new Date());
            return complaintDelegateInterfaceImpl.saveComplaint(complaint);
        }
        return null;

    }

    @Override
    public boolean adminAcceptComplaint(String adminEmail, Integer complaintId) {

        try {
            AdminTable admin = complaintDelegateInterfaceImpl.checkAdminExistById(adminEmail);
            Complaint complaint = complaintDelegateInterfaceImpl.checkComplaintExsistById(complaintId);
            if (admin != null && complaint != null && complaint.getState().equals(Complaint.ON_REVIEW_STATE) == true) {
                complaint.setState(Complaint.ACCEPTED_STATE);
                complaint.setReviewedBy(admin);
                complaintDelegateInterfaceImpl.saveComplaint(complaint);
                return true;
            }
        }catch (Exception e)
        {
            return false;
        }
        return false;

    }

    @Override
    public boolean adminRejectComplaint(String adminEmail, Integer complaintId) {
        try {
            AdminTable admin = complaintDelegateInterfaceImpl.checkAdminExistById(adminEmail);
            Complaint complaint = complaintDelegateInterfaceImpl.checkComplaintExsistById(complaintId);
            if (admin != null && complaint != null && complaint.getState().equals(Complaint.ON_REVIEW_STATE) == true) {
                complaint.setState(Complaint.REJECTED_STATE);
                complaint.setReviewedBy(admin);
                complaintDelegateInterfaceImpl.saveComplaint(complaint);
                return true;
            }
        }catch (Exception e)
        {
            return false;
        }
        return false;
    }

    @Override
    public boolean adminReviewComplaint(String adminEmail, Integer complaintId) {
        try {
            AdminTable admin = complaintDelegateInterfaceImpl.checkAdminExistById(adminEmail);
            Complaint complaint = complaintDelegateInterfaceImpl.checkComplaintExsistById(complaintId);
            System.out.println("here0"+admin);
            if (admin != null && complaint != null && complaint.getState().equals(Complaint.NOT_REVIEWED_STATE) == true) {
                System.out.println("here");
                complaint.setState(Complaint.ON_REVIEW_STATE);
                complaint.setReviewedBy(admin);//l by8yr l state hoa l byt7t w ay 7ad y2dr y8yr
                complaintDelegateInterfaceImpl.saveComplaint(complaint);
                return true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
