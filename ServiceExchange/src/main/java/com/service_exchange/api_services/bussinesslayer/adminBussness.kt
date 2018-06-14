package com.service_exchange.api_services.bussinesslayer

import com.service_exchange.api_services.bussinessdaodelegates.Admin.*
import com.service_exchange.api_services.dao.dto.*
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface AdminBussnessStatic {
    fun getComplaintsStatic(): ComplaintStatics
    fun getServiceStatic(): ServiceStatices
    fun getTransactionStatic(): TransactionStatices
    fun getUserStatic(): UserStatices
    fun getSkillStatices(): SkillStatistics

}

@Component
private class AdminBussnessStaticImpl : AdminBussnessStatic {


    @Autowired
    lateinit var adminStatic: AdminStatic

    override fun getSkillStatices(): SkillStatistics =
            SkillStatistics().apply {
                mainCount = adminStatic.getNumberOfMainCatagorys().toInt()
                totalSkillCount = adminStatic.getNumberOfSkills().toInt()
            }
    override fun getComplaintsStatic(): ComplaintStatics =
            ComplaintStatics().let { comp ->
                comp.acceptedCount = adminStatic.getAcceptedComplaint().toInt()
                comp.completedCount = adminStatic.getCompletedComplaint().toInt()
                comp.notReveiwCount = adminStatic.getNotReviwedCompliantCount().toInt()
                comp.onReviewCount = adminStatic.getOnReviewComPlaintCount().toInt()
                comp.rejectedCount = adminStatic.getRejectedComplaint().toInt()
                comp
            }


    override fun getServiceStatic(): ServiceStatices =
            ServiceStatices().apply {
                offeredCount = adminStatic.getNumberOfOffers().toInt()
                reqCount = adminStatic.getNumberOfRequset().toInt()
                pausedCount = adminStatic.getPausedService().toInt()
            }

    override fun getTransactionStatic(): TransactionStatices =
            TransactionStatices().apply {
                activeCount = adminStatic.getNumberOfActiveTransactions().toInt()
                waitingCount = adminStatic.getNumberWaitingTransactions().toInt()
                completedCount = adminStatic.getNumberOfCompletedTransactions().toInt()
                lateCompletedCount = adminStatic.getNumberOfCompletedTransactions().toInt()
                caneledCount = adminStatic.getNumberOfCanceledTransactions().toInt()
                totalPointCount = adminStatic.getTotalPointOnServices().toInt()
            }

    override fun getUserStatic(): UserStatices =
            UserStatices().apply {
                userCount = adminStatic.getNumberOfUser().toInt()
                onlineCount = adminStatic.getUserCountBasedOnStatus(UserTable.ONLINE).toInt()
                busyCount = adminStatic.getUserCountBasedOnStatus(UserTable.BUSY).toInt()
                offlineCount = adminStatic.getUserCountBasedOnStatus(UserTable.OFFLINE).toInt()
            }

}

interface AdminBussnessGettable {
    fun getAllNotifecation(page: Int): List<NotificationDto>
    fun getAllUsersWithSimpleDetails(page: Int): List<UserAdminInfo>
    fun getAllServiceWithSimpleDetails(page: Int, type: String): List<ServiceAdminInfo>
    fun getAdminInfo(adminId: String): AdminMain?
    fun getAdminChallanges(adminId: String, page: Int): List<AdminChallange>
    fun getAdminOpenComplains(adminId: String, page: Int): List<AdminComplaint>
    fun getAdminNotifecation(adminId: String, page: Int): List<AdminNotification>
    fun getAllComlaintsTransactionChat(compaintId: Int): List<MessageGeneralDto>
    fun getAllComlaintChat(compaintId: Int): List<MessageGeneralDto>
    fun getAllUnReviewedComplaint(page: Int): List<AdminComplaint>
    fun getAllAdmins(page: Int): List<AdminMain>
}

@Component
private class AdminBussnessGettableImpl : AdminBussnessGettable {
    @Autowired
    lateinit var adminStatic: AdminStatic
    @Autowired
    lateinit var adminGettable: AdminGettable

    override fun getAllAdmins(page: Int): List<AdminMain> =
            adminGettable.getAlladmins(page)

    override fun getAllUnReviewedComplaint(page: Int): List<AdminComplaint> =
            adminGettable.getAllOpenedComplaints(page)

    override fun getAllNotifecation(page: Int): List<NotificationDto> =
            adminStatic.getAllNotifecation(page)

    override fun getAllUsersWithSimpleDetails(page: Int): List<UserAdminInfo> =
            adminStatic.getAllUsersWithSimpleDetails(page)

    override fun getAllServiceWithSimpleDetails(page: Int, type: String): List<ServiceAdminInfo> =
            adminStatic.getAllServiceWithSimpleDetails(page, type)

    override fun getAdminInfo(adminId: String): AdminMain? =
            adminGettable.getAdminInfo(adminId)

    override fun getAdminChallanges(adminId: String, page: Int): List<AdminChallange> =
            adminGettable.getAdminChallanges(adminId, page)

    override fun getAdminOpenComplains(adminId: String, page: Int): List<AdminComplaint> =
            adminGettable.getAdminOpenComplains(adminId, page)

    override fun getAdminNotifecation(adminId: String, page: Int): List<AdminNotification> =
            adminGettable.getAdminNotifecation(adminId, page)

    override fun getAllComlaintsTransactionChat(compaintId: Int): List<MessageGeneralDto> =
            adminGettable.getAllComlaintsTransactionChat(compaintId)

    override fun getAllComlaintChat(compaintId: Int): List<MessageGeneralDto> =
            adminGettable.getAllComlaintChat(compaintId)

}

interface AdminBussnessCrud {
    fun enableAdmin(adminId: String): Boolean
    fun enableUser(userId: Int): Boolean
    fun enableService(serviceId: Int): Boolean
    fun acceptComplaint(compaintId: Int, adminId: String): Boolean
    fun approveSkill(skillId: Int): Boolean
    fun restoreSkill(skillId: Int): Boolean
    fun addSkill(skillDTO: SkillDTO): SkillDTO?
    fun createAdmin(admin: AdminMain): AdminMain?
    fun disableService(serviceId: Int): Boolean
    fun disableAdmin(adminId: String): Boolean
    fun disableUser(userId: Int): Boolean
    fun disableSkill(skillId: Int): Boolean
    fun deleteSkill(skillId: Int): Boolean
    fun rejectComplaint(compaintId: Int): Boolean
}

@Component
private class AdminBussnessCrudImpl : AdminBussnessCrud {
    @Autowired
    lateinit var adminAccept: AdminAccept
    @Autowired
    lateinit var adminCancel: AdminCancel
    @Autowired
    lateinit var adminCreate: AdminCreate

    override fun enableAdmin(adminId: String): Boolean =
            adminAccept.enableAdmin(adminId)

    override fun enableUser(userId: Int): Boolean =
            adminAccept.enableUser(userId)

    override fun enableService(serviceId: Int): Boolean =
            adminAccept.enableService(serviceId)

    override fun acceptComplaint(compaintId: Int, adminId: String): Boolean =
            adminAccept.acceptComplaint(compaintId, adminId)

    override fun approveSkill(skillId: Int): Boolean =
            adminAccept.approveSkill(skillId)

    override fun restoreSkill(skillId: Int): Boolean =
            adminAccept.restoreSkill(skillId)

    override fun addSkill(skillDTO: SkillDTO): SkillDTO? =
            adminCreate.addSkill(skillDTO)

    override fun createAdmin(admin: AdminMain): AdminMain? =
            adminCreate.createAdmin(admin)

    override fun disableService(serviceId: Int): Boolean =
            adminCancel.disableService(serviceId)

    override fun disableAdmin(adminId: String): Boolean =
            adminCancel.disableAdmin(adminId)

    override fun disableUser(userId: Int): Boolean =
            adminCancel.disableUser(userId)

    override fun disableSkill(skillId: Int): Boolean =
            adminCancel.disableSkill(skillId)

    override fun deleteSkill(skillId: Int): Boolean =
            adminCancel.deleteSkill(skillId)

    override fun rejectComplaint(compaintId: Int): Boolean =
            adminCancel.rejectComplaint(compaintId)

}