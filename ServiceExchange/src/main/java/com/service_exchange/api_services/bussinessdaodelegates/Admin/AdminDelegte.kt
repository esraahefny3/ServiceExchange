package com.service_exchange.api_services.bussinessdaodelegates.Admin

import com.service_exchange.api_services.bussinessdaodelegates.skill.convertSkillAlone
import com.service_exchange.api_services.dao.admin.AdminInterface
import com.service_exchange.api_services.dao.complaint.ComplaintInterface
import com.service_exchange.api_services.dao.dto.*
import com.service_exchange.api_services.dao.notification.NotificationDataInterface
import com.service_exchange.api_services.dao.service.ServiceInterface
import com.service_exchange.api_services.dao.skill.SkillInterface
import com.service_exchange.api_services.dao.transaction.AdminStaticTransaction
import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.api_services.factories.AppFactory
import com.service_exchange.entities.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.util.stream.Collectors

fun AdminTable.convertAdmin() =
        AppFactory.mapToDto(this, AdminMain::class.java)

fun Challenge.convertAdminChallenge() =
        AppFactory.mapToDto(this, AdminChallange::class.java)

fun Complaint.convertAdminComplaint() =
        AppFactory.mapToDto(this, AdminComplaint::class.java)

fun Notification.convertAdminNotifecation() =
        AppFactory.mapToDto(this, AdminNotification::class.java)

fun Notification.convertNotifecationDTO() =
        AppFactory.mapToDto(this, NotificationDto::class.java)

fun Message.convertToTransaction() =
        AppFactory.mapToDto(this, MessageTransactionDto::class.java)

fun Message.convertToComplain() =
        AppFactory.mapToDto(this, MessageComplaintDto::class.java)

fun SkillDTO.convertToSkill() =
        AppFactory.mapToDto(this, Skill::class.java)

fun AdminMain.convertToAdmin() =
        AppFactory.mapToDto(this, AdminTable::class.java)

interface AdminGettable {
    fun getAdminInfo(adminId: String): AdminMain?
    fun getAdminChallanges(adminId: String, page: Int): List<AdminChallange>
    fun getAdminOpenComplains(adminId: String, page: Int): List<AdminComplaint>
    fun getAdminNotifecation(adminId: String, page: Int): List<AdminNotification>
    fun getAllComlaintsTransactionChat(compaintId: Int): List<MessageTransactionDto>
    fun getAllComlaintChat(compaintId: Int): List<MessageComplaintDto>
}

@Component
private class AdminGettableImpl : AdminGettable {
    @Autowired
    lateinit var adminInterface: AdminInterface
    @Autowired
    lateinit var complainInterface: ComplaintInterface

    override fun getAdminInfo(adminId: String): AdminMain? =
            adminInterface.getAdmin(adminId)?.convertAdmin()

    override fun getAdminChallanges(adminId: String, page: Int): List<AdminChallange> =
            adminInterface.getAdmin(adminId)?.challengeCollection?.stream()?.map { it.convertAdminChallenge() }
                    ?.collect(Collectors.toList())
                    ?.filterIndexed { index, adminChallange -> (index < (page * 20) + 20) && (index >= page * 20) }
                    ?: emptyList()

    override fun getAdminOpenComplains(adminId: String, page: Int): List<AdminComplaint> =
            adminInterface.getAdmin(adminId)?.complaintCollection?.stream()?.map { it.convertAdminComplaint() }?.collect(Collectors.toList())
                    ?.filterIndexed { index, adminChallange -> (index < (page * 20) + 20) && (index >= page * 20) }
                    ?: emptyList()

    override fun getAdminNotifecation(adminId: String, page: Int): List<AdminNotification> =
            adminInterface.getAdmin(adminId)?.notificationCollection?.stream()?.map { it.convertAdminNotifecation() }
                    ?.collect(Collectors.toList())?.filterIndexed { index, adminChallange -> (index < (page * 20) + 20) && (index >= page * 20) }
                    ?: emptyList()

    override fun getAllComlaintsTransactionChat(compaintId: Int): List<MessageTransactionDto> =
            complainInterface.getComplaintById(compaintId)?.transactionId?.messageCollection?.stream()?.map { t -> t.convertToTransaction() }
                    ?.collect(Collectors.toList()) ?: emptyList()

    override fun getAllComlaintChat(compaintId: Int): List<MessageComplaintDto> =
            complainInterface.getComplaintById(compaintId)?.messageCollection?.stream()?.map { t -> t.convertToComplain() }
                    ?.collect(Collectors.toList()) ?: emptyList()
}

interface AdminCreate {

    fun addSkill(skillDTO: SkillDTO): SkillDTO?
    fun createAdmin(admin: AdminMain): AdminMain?
}

@Component
private class AdminCreateImpl : AdminCreate {
    @Autowired
    lateinit var skillInterface: SkillInterface
    @Autowired
    lateinit var adminInterface: AdminInterface

    override fun addSkill(skillDTO: SkillDTO): SkillDTO? =
            skillInterface.createSkill(skillDTO.convertToSkill())?.convertSkillAlone()

    override fun createAdmin(admin: AdminMain): AdminMain? {
        adminInterface.getAdmin(admin.email ?: "")?.let { return null }
        adminInterface.save(admin.convertToAdmin()).let { return it.convertAdmin() }
    }

}

interface AdminUpdate {

    fun updateAdmin(admin: AdminMain): AdminMain?
}

@Component
private class AdminUpdateImpl : AdminUpdate {
    @Autowired
    lateinit var adminInterface: AdminInterface

    override fun updateAdmin(admin: AdminMain): AdminMain? =
            admin.email?.let {
                adminInterface.getAdmin(admin.email ?: "")?.let {
                    it.name = admin.name
                    it.image = admin.image
                    it.password = admin.password
                    adminInterface.save(it).convertAdmin()
                }
            }


}

interface AdminAccept {
    fun enableAdmin(adminId: String): Boolean
    fun enableUser(userId: Int): Boolean
    fun enableService(serviceId: Int): Boolean
    fun acceptComplaint(compaintId: Int): Boolean
    fun approveSkill(skillId: Int): Boolean
    fun restoreSkill(skillId: Int): Boolean
}

@Component
private class AdminAcceptImpl : AdminAccept {
    @Autowired
    lateinit var adminInterface: AdminInterface
    @Autowired
    lateinit var userInterface: UserInterFace
    @Autowired
    lateinit var serviceInterface: ServiceInterface
    @Autowired
    lateinit var complaintInterface: ComplaintInterface
    @Autowired
    lateinit var skillInterface: SkillInterface

    override fun enableAdmin(adminId: String): Boolean {
        adminInterface.getAdmin(adminId)?.let {
            it.isEnabled = AdminTable.ENDABLED
            adminInterface.save(it)
            return true
        }
        return false
    }

    override fun enableUser(userId: Int): Boolean {
        userInterface.getUser(userId)?.let {
            it.setIsEnabled(UserTable.ENABELED)
            userInterface.updateUser(it)
            return true
        }
        return false
    }

    override fun enableService(serviceId: Int): Boolean {
        serviceInterface.getService(serviceId)?.let {
            it.isAvailable = Service.AVALIBLE
            serviceInterface.modifieService(it)
            return true
        }
        return false
    }

    override fun acceptComplaint(compaintId: Int): Boolean {
        complaintInterface.getComplaintById(compaintId)?.takeIf {
            if (it.state == Complaint.NOT_REVIEWED_STATE)
                return true
            else false
        }?.let {
            it.state = Complaint.ACCEPTED_STATE
            complaintInterface.saveComplaint(it)
            return true
        }
        return false
    }


    override fun approveSkill(skillId: Int): Boolean {
        skillInterface.getSkillById(skillId)?.let {
            it.isVerified = Skill.VIREFIED
            skillInterface.updateSkill(it)
            return true
        }
        return false
    }

    override fun restoreSkill(skillId: Int): Boolean {
        skillInterface.getSkillById(skillId)?.let {
            it.isDeleted = Skill.NOTDELETED
            skillInterface.updateSkill(it)
            return true
        }
        return false
    }

}

interface AdminCancel {
    fun disableService(serviceId: Int): Boolean
    fun disableAdmin(adminId: String): Boolean
    fun disableUser(userId: Int): Boolean
    fun disableSkill(skillId: Int): Boolean
    fun deleteSkill(skillId: Int): Boolean
    fun rejectComplaint(compaintId: Int): Boolean
}

@Component
private class AdminCancelImpl : AdminCancel {
    @Autowired
    lateinit var adminInterface: AdminInterface
    @Autowired
    lateinit var userInterface: UserInterFace
    @Autowired
    lateinit var serviceInterface: ServiceInterface
    @Autowired
    lateinit var complaintInterface: ComplaintInterface
    @Autowired
    lateinit var skillInterface: SkillInterface

    override fun disableService(serviceId: Int): Boolean {
        serviceInterface.getService(serviceId)?.takeIf { it.isAvailable != Service.DELETED }?.let {
            it.isAvailable = Service.PAUSED
            serviceInterface.modifieService(it)
            return true
        }
        return false
    }

    override fun disableAdmin(adminId: String): Boolean {
        adminInterface.getAdmin(adminId = adminId)?.let {
            it.isEnabled = AdminTable.DISABLED
            adminInterface.save(it)
            return true
        }
        return false
    }

    override fun disableUser(userId: Int): Boolean {
        userInterface.getUser(userId)?.let {
            it.setIsEnabled(UserTable.DISABLED)
            userInterface.updateUser(it)
            return false
        }
        return true
    }

    override fun disableSkill(skillId: Int): Boolean {
        skillInterface.getSkillById(skillId = skillId)?.let {
            it.isVerified = Skill.NOTVIREFIED
            skillInterface.updateSkill(it)
            return true
        }
        return false
    }

    override fun deleteSkill(skillId: Int): Boolean {
        skillInterface.getSkillById(skillId = skillId)?.let {
            it.isDeleted = Skill.DELETED
            skillInterface.updateSkill(it)
            return true
        }
        return false
    }

    override fun rejectComplaint(compaintId: Int): Boolean {
        complaintInterface.getComplaintById(compaintId)?.let {
            it.state = Complaint.REJECTED_STATE
            complaintInterface.saveComplaint(it)
            return true
        }
        return false
    }

}

interface AdminStatic {
    fun getNumberOfService(): Long
    fun getNumberOfUser(): Long
    fun getNumberOfRequset(): Long
    fun getNumberOfOffers(): Long
    fun getNumberOfSkills(): Long
    fun getNumberOfMainCatagorys(): Long
    fun getComplaintCount(): Long
    fun getOnReviewComPlaintCount(): Long
    fun getNotReviwedCompliantCount(): Long
    fun getRejectedComplaint(): Long
    fun getAcceptedComplaint(): Long
    fun getNumberOfActiveTransactions(): Long
    fun getAllNotifecation(page: Int): List<NotificationDto>
    fun getAllUsersWithSimpleDetails(page: Int): List<UserAdminInfo>
    fun getAllServiceWithSimpleDetails(page: Int, type: String): List<ServiceAdminInfo>
}

@Component
private class AdminStaticImpl : AdminStatic {


    @Autowired
    lateinit var adminStaticTransaction: AdminStaticTransaction
    @Autowired
    lateinit var adminInterface: NotificationDataInterface
    @Autowired
    lateinit var userInterface: UserInterFace
    @Autowired
    lateinit var serviceInterface: ServiceInterface
    @Autowired
    lateinit var complaintInterface: ComplaintInterface
    @Autowired
    lateinit var skillInterface: SkillInterface

    override fun getNumberOfService(): Long =
            serviceInterface.countAllservice()

    override fun getNumberOfUser(): Long =
            userInterface.userCount

    override fun getNumberOfRequset(): Long =
            serviceInterface.countAllRequset()

    override fun getNumberOfOffers(): Long =
            serviceInterface.countAllOffers()

    override fun getNumberOfSkills(): Long =
            skillInterface.getNumberOfSkills()

    override fun getNumberOfMainCatagorys(): Long =
            skillInterface.getNumberOfParentSkills()

    override fun getComplaintCount(): Long =
            complaintInterface.getCountOfComplaint()

    override fun getOnReviewComPlaintCount(): Long =
            complaintInterface.getCountOfOnReviewComplaint()

    override fun getNotReviwedCompliantCount(): Long =
            complaintInterface.getCountOfNotReviewComplaint()

    override fun getRejectedComplaint(): Long =
            complaintInterface.getCountOfRejectedComplaint()

    override fun getAcceptedComplaint(): Long =
            complaintInterface.getCountOfAcceptedComplaint()

    override fun getNumberOfActiveTransactions(): Long =
            adminStaticTransaction.countAllByStateEquals(TransactionInfo.ON_PROGRESS_STATE)

    override fun getAllNotifecation(page: Int): List<NotificationDto> =
            adminInterface.findAll(PageRequest.of(page, 20)).stream()
                    .map { it.convertNotifecationDTO() }.collect(Collectors.toList())

    override fun getAllUsersWithSimpleDetails(page: Int): List<UserAdminInfo> =
            userInterface.getAllUser(page).stream().map {
                val user = AppFactory.mapToDto(it, UserAdminInfo::class.java)
                user.pointSpend = it.transactionInfoCollection?.stream()?.filter { it.type == Service.OFFERED && (it.state == TransactionInfo.COMPLETED_STATE || it.state == TransactionInfo.LATE_STATE) }?.mapToInt { value ->
                    value.price ?: 0
                }
                        ?.sum()
                user.PointEarned = it.serviceCollection?.stream()?.filter { it.type == Service.OFFERED }
                        ?.mapToInt {
                            it.transactionInfoCollection?.stream()?.filter { (it.state == TransactionInfo.COMPLETED_STATE || it.state == TransactionInfo.LATE_STATE) }
                                    ?.mapToInt { it.price ?: 0 }?.sum() ?: 0
                        }
                        ?.sum()
                user.reivews = it.serviceCollection?.stream()?.filter { it.type == Service.OFFERED }
                        ?.mapToLong {
                            it.transactionInfoCollection?.stream()?.filter { (it.state == TransactionInfo.COMPLETED_STATE || it.state == TransactionInfo.LATE_STATE) }
                                    ?.count() ?: 0
                        }
                        ?.sum()?.toInt()
                user.services = it.serviceCollection?.stream()?.filter { it.type == Service.OFFERED }
                        ?.count()?.toInt()
                user.requests = it.serviceCollection?.stream()?.filter { it.type == Service.REQUSETED }
                        ?.count()?.toInt()
                user
            }.collect(Collectors.toList())


    override fun getAllServiceWithSimpleDetails(page: Int, type: String): List<ServiceAdminInfo> =
            serviceInterface.getAll(page).stream().filter { it.type == type }.map {
                val service = AppFactory.mapToDto(it, ServiceAdminInfo::class.java)
                service.category = it.skillCollection?.stream()?.map { it.description }?.collect(Collectors.toList())?.joinToString(",")
                service.ownerImage = it.madeBy?.image
                service.ownerName = it.madeBy?.name
                service.reviewsCount = it.transactionInfoCollection?.stream()?.filter {
                    it.state == TransactionInfo.COMPLETED_STATE
                            || it.state == TransactionInfo.LATE_STATE
                }?.count()?.toInt()
                service.startedDated = it.startDate?.time
                service

            }.collect(Collectors.toList())



}
