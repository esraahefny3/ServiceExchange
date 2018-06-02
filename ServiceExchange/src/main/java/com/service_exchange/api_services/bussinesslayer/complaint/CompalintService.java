/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer.complaint;

import com.service_exchange.api_services.bussinessdaodelegates.complaintdelegate.ComplaintDelegateInterface;
import com.service_exchange.api_services.dao.complaint.complaintdtos.ComplaintDto;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author esraa
 */
  public class CompalintService  implements ComplaintServiceInterface{

      @Autowired
      private ComplaintDelegateInterface complaintDelegateInterfaceImpl;
    @Override
    public ComplaintDto userMakeComplaintOnTransaction(Integer userId, Integer transactionId, Complaint complaint) {
    
            UserTable user=complaintDelegateInterfaceImpl.checkUserExistById(userId);
            TransactionInfo transactionInfo=complaintDelegateInterfaceImpl.checkTransactionExistById(transactionId);
            return null;
    
    }

    @Override
    public ComplaintDto userMakeComplaintGeneral(Integer userId, Complaint complaint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean adminApproveComplaint(String adminEmail, Integer complaintId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean adminRejectComplaint(String adminEmail, Integer complaintId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
