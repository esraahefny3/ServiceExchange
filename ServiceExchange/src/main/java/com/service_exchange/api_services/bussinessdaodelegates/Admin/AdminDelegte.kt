package com.service_exchange.api_services.bussinessdaodelegates.Admin

import com.service_exchange.api_services.dao.dto.*

interface AdminGettable {
    fun getAdminInfo(adminId: Int): AdminMain
    fun getAdminChallanges(adminId: Int): List<AdminChallange>
    fun getAdminOpenComplains(adminId: Int): List<AdminComplaint>
    fun getAdminNotifecation(adminId: Int): List<AdminNotification>
    fun getAllComlaintsTransactionChat(compaintId: Int): List<MessageGeneralDto>
}

interface AdminCreate {

    fun addSkill(skillDTO: SkillDTO)
    fun createAdmin(admin: AdminMain): AdminMain
}

interface AdminUpdate {
    fun updateAdmin(admin: AdminMain)
}

interface AdminAccept {
    fun enableAdmin(adminId: Int)
    fun enableUser(userId: Int)
    fun enableService(serviceId: Int)
    fun acceptComplaint(compaintId: Int)
    fun approveSkill(skillId: Int)
}

interface AdminCancel {
    fun disableService(serviceId: Int)
    fun disableAdmin(adminId: Int)
    fun disableUser(userId: Int)
    fun disableSkill(SkillId: Int)
    fun deleteSkill(skillId: Int)
    fun rejectComplaint(compaintId: Int)
}
