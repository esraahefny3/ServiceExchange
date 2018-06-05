package com.service_exchange.api_services.bussinessdaodelegates.Admin

import com.service_exchange.api_services.dao.dto.*
import com.service_exchange.entities.UserTable

interface AdminGettable {
    fun getAllUser(page: Int): List<UserTable>
    fun getAllSkills(page: Int): List<SkillDTO>
    fun getAdminInfo(adminId: Int): AdminMain
    fun getAdminChallanges(adminId: Int): List<AdminChallange>
    fun getAdminOpenComplains(adminId: Int): List<AdminComplaint>
    fun getAdminNotifecation(adminId: Int): List<AdminNotification>
    fun createAdmin(admin: AdminMain): AdminMain
    fun disableAdmin(adminId: Int)
    fun enableAdmin(adminId: Int)
    fun disableUser(userId: Int)
    fun enableUser(userId: Int)
    fun enableService(serviceId: Int)
    fun disableService(serviceId: Int)
    fun getAllComplaints(): List<ComplaintDto>
    fun getAllComlaintsTransactionChat(compaintId: Int): List<MessageTransactionDto>
    fun acceptComplaint(compaintId: Int)
    fun rejectComplaint(compaintId: Int)
    fun sendMessageOnComplaint(compaintId: Int, massage: MessageComplaintDto)
    fun takeActionOnComplaint()
    fun approveSkill(skillId: Int)
    fun addSkill(skillDTO: SkillDTO)
    fun deleteSkill(skillId: Int)
    fun updateAdmin(admin: AdminMain)
}

interface AdminModifies {

}

interface AdminDelete {

}
