 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinessdaodelegates.complaintdelegate;

import com.service_exchange.api_services.dao.dto.ComplaintDto;
import com.service_exchange.entities.AdminTable;
import com.service_exchange.entities.Complaint;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;

/**
 *
 * @author esraa
 */
public interface ComplaintDelegateInterface {
    public ComplaintDto saveComplaint(Complaint complaint);
    public UserTable checkUserExistById(Integer userId);
    public TransactionInfo checkTransactionExistById(Integer transactionId);
    public Complaint checkComplaintExsistById(Integer complaintId);
    public AdminTable checkAdminExistById(String adminEmail);
    public boolean checkUserMadeComplaintIntTransaction(Integer userId,Integer transactionId);
}
