/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.bussinesslayer.complaint;

import com.service_exchange.api_services.dao.dto.ComplaintDto;
import com.service_exchange.entities.Complaint;

/**
 *
 * @author esraa
 */
public interface ComplaintServiceInterface {
    public ComplaintDto userMakeComplaintOnTransaction(Integer userId ,Integer transactionId ,Complaint complaint);
    public ComplaintDto userMakeComplaintGeneral(Integer userId , Complaint complaint);
    public boolean adminAcceptComplaint(String adminEmail,Integer complaintId);
    public boolean adminRejectComplaint(String adminEmail,Integer complaintId);
    public boolean adminReviewComplaint(String adminEmail,Integer complaintId);
}
