package com.service_exchange.api_services.dao.admin

import com.service_exchange.entities.Complaint
import com.service_exchange.entities.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

interface AdminComplaintInterface {
    fun getAllReviewedComplainsByAdmin(adminId: Int): List<Complaint>?
    fun reviewComplain(adminId: Int, complaintId: Int): Boolean
    fun updateComplain(adminId: Int, complaint: Complaint): Complaint
    fun sendMessageThroughComplain(complaintId: Int, message: Message): Boolean
    fun getAllMessageInComplaint(complaintId: Int): List<Message>
    fun rejectComplaint(complaintId: Int): Boolean
    fun activateComplaint(complaintId: Int): Boolean

}

@Component
private class AdminComplaintImpl : AdminComplaintInterface {
    @Autowired
    lateinit var adminData: AdminInterface

    override fun getAllReviewedComplainsByAdmin(adminId: Int): List<Complaint>? =
            adminData.getAdmin(adminId)?.complaintCollection?.stream()?.collect(Collectors.toList())


    override fun reviewComplain(adminId: Int, complaintId: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateComplain(adminId: Int, complaint: Complaint): Complaint {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendMessageThroughComplain(complaintId: Int, message: Message): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllMessageInComplaint(complaintId: Int): List<Message> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rejectComplaint(complaintId: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activateComplaint(complaintId: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}