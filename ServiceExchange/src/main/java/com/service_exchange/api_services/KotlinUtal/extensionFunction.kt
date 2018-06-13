package com.service_exchange.api_services.KotlinUtal

import com.service_exchange.api_services.dao.dto.*
import com.service_exchange.api_services.dao.skill.SkillInterface
import com.service_exchange.api_services.dao.transaction.TransactionDto
import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.api_services.factories.AppFactory
import com.service_exchange.entities.*
import java.util.stream.Collectors


fun Service.convertServie(): ServiceDTO =
        this.let { t ->
            val ob = AppFactory.mapToDto(t, ServiceDTO::class.java)
            ob.available = t.available
            ob.skillList = t.skillCollection?.stream()?.map { it.id }?.collect(Collectors.toList())
            ob.uO = com.service_exchange.api_services.dao.dto.UserInfo(t.madeBy?.name, t.madeBy?.id, t.madeBy?.image)
            ob.numberOfTransaction = t.transactionInfoCollection?.stream()?.filter({
                it.state == TransactionInfo.COMPLETED_STATE
                        || it.state == TransactionInfo.LATE_STATE
            })?.count()?.toInt()
            ob.rating = t.transactionInfoCollection?.stream()?.filter { it.state == TransactionInfo.COMPLETED_STATE || it.state == TransactionInfo.LATE_STATE }
                    ?.mapToDouble {
                        it.reviewCollection?.stream()
                                ?.mapToDouble { it.rating?.toDouble() ?: 0.0 }?.average()?.orElse(0.0) ?: 0.0
                    }?.average()?.orElse(0.0)
            ob.expectDur = t.duration?.toLong()
            ob.revList = kotlin.collections.mutableListOf()
            t.transactionInfoCollection?.stream()?.map {
                it.reviewCollection?.stream()?.map { it.convertReview() }?.forEach { ob.revList?.add(it) }
            }

            ob
        }

fun Review.convertReview(): ReviewDTO =
        ReviewDTO().apply {
            id = this@convertReview.id
            comment = this@convertReview.comment
            rating = this@convertReview.rating
            transactionId = this@convertReview.transactionId?.id
            userInfo = this@convertReview.madeBy?.let { com.service_exchange.api_services.dao.dto.UserInfo(it.name, it.id, it.image) }
        }


fun ServiceDTO.convertServie(skillInterface: SkillInterface, userInterface: UserInterFace): Service =
        this.let { t ->
            val ob = AppFactory.mapToDto(t, Service::class.java)
            ob.skillCollection = t.skillList?.stream()?.map { skillInterface.getSkillById(it) }
                    ?.collect(Collectors.toList()) ?: kotlin.collections.emptyList()
            ob.madeBy = userInterface.getUser(t.uO?.id)
            t.expectDur?.let { l -> ob.startDate = java.util.Date(l) }
            t.duration?.let { ob.duration = it.toInt() }
            ob.available = t.available

            ob
        }

fun TransactionInfo.convert(): TransactionDto =
        AppFactory.mapToDto(this, TransactionDto::class.java).apply {

        }


fun Skill.convertSkill(): SkillDTO {

    val skill = AppFactory.mapToDto(this, SkillDTO::class.java)
    skill.rating = this.reviewSkillCollection.stream().mapToDouble { it.rating.toDouble() }.average().orElse(0.0)
    return skill
}

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

fun Message.convertToDefult() =
        AppFactory.mapToDto(this, MessageGeneralDto::class.java).apply {
            transactionId = this@convertToDefult.transactionId?.id
            receiverId = this@convertToDefult.receiverId?.id
            senderId = this@convertToDefult.senderId?.id
            userReadIt = this@convertToDefult.isSeen
            readDate = this@convertToDefult.seenDate
            complaintId = this@convertToDefult.complaintId?.id
        }

fun Message.convertToComplain() =
        AppFactory.mapToDto(this, MessageComplaintDto::class.java)

fun SkillDTO.convertToSkill() =
        AppFactory.mapToDto(this, Skill::class.java)

fun AdminMain.convertToAdmin() =
        AppFactory.mapToDto(this, AdminTable::class.java)
