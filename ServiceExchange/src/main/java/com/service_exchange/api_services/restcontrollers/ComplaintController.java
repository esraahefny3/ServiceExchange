package com.service_exchange.api_services.restcontrollers;

import com.service_exchange.api_services.bussinesslayer.complaint.ComplaintServiceInterface;
import com.service_exchange.api_services.dao.dto.ComplaintDto;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ComplaintController {
//
//
//    public ComplaintDto userMakeComplaintOnTransaction(Integer userId ,Integer transactionId ,Complaint complaint);==>
//    public ComplaintDto userMakeComplaintGeneral(Integer userId ,Complaint complaint);==>
//    public boolean adminAcceptComplaint(String adminEmail,Integer complaintId);
//    public boolean adminRejectComplaint(String adminEmail,Integer complaintId);
//    public boolean adminReviewComplaint(String adminEmail,Integer complaintId);
    @Autowired
    private ComplaintServiceInterface complaintServiceInterface;

    private Integer pageSize = 20;

    @RequestMapping(value = "/userMakeComplaintOnTransaction", method = RequestMethod.POST)
    public ComplaintDto userMakeComplaintOnTransaction(@RequestBody ComplaintDto complaintDto) {

        if (complaintDto != null) {

            if (complaintDto.getUserId() != null && complaintDto.getTransactionId() != null && complaintDto.getDescription() != null) {
                Complaint complaint=AppFactory.mapToEntity(complaintDto,Complaint.class);
                return complaintServiceInterface.userMakeComplaintOnTransaction(complaintDto.getUserId(),complaintDto.getTransactionId(),complaint);
            }
        }

        return null;
    }

    @RequestMapping(value = "/adminAcceptComplaint", method = RequestMethod.POST)
    public boolean adminAcceptComplaint(@RequestBody Map<String ,Object> mapData) {

        if (mapData != null) {

           String adminEmail= (String) mapData.get("adminEmail");
           Integer complaintId=(Integer)mapData.get("complaintId");
           if(adminEmail!=null&&complaintId!=null)
           {
               return complaintServiceInterface.adminAcceptComplaint(adminEmail,complaintId);
           }

        }

        return false;
    }

    @RequestMapping(value = "/adminRejectComplaint", method = RequestMethod.POST)
    public boolean adminRejectComplaint(@RequestBody Map<String ,Object> mapData) {

        if (mapData != null) {

            String adminEmail= (String) mapData.get("adminEmail");
            Integer complaintId=(Integer)mapData.get("complaintId");
            if(adminEmail!=null&&complaintId!=null)
            {
                return complaintServiceInterface.adminRejectComplaint(adminEmail,complaintId);
            }

        }

        return false;
    }
    @RequestMapping(value = "/adminReviewComplaint", method = RequestMethod.POST)
    public boolean adminReviewComplaint(@RequestBody Map<String ,Object> mapData) {

        if (mapData != null) {

            String adminEmail= (String) mapData.get("adminEmail");
            Integer complaintId=(Integer)mapData.get("complaintId");
            if(adminEmail!=null&&complaintId!=null)
            {
                return complaintServiceInterface.adminReviewComplaint(adminEmail,complaintId);
            }

        }

        return false;
    }
    @RequestMapping(value = "/userMakeComplaintGeneral", method = RequestMethod.POST)
    public ComplaintDto userMakeComplaintGeneral(@RequestBody ComplaintDto complaintDto) {

        if (complaintDto != null) {

            if (complaintDto.getUserId() != null && complaintDto.getDescription() != null) {
                Complaint complaint=AppFactory.mapToEntity(complaintDto,Complaint.class);
                return complaintServiceInterface.userMakeComplaintGeneral(complaintDto.getUserId(),complaint);
            }
        }

        return null;
    }
//
}
