package com.service_exchange.api_services.KotlinUtal

import com.service_exchange.api_services.dao.dto.*
import com.service_exchange.api_services.dao.skill.SkillInterface
import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.api_services.factories.AppFactory
import com.service_exchange.entities.*
import java.util.stream.Collectors


fun Service.convertServie(): ServiceDTO =
        this.let { t ->
            val ob = AppFactory.mapToDto(t, ServiceDTO::class.java)
            ob.skillList = t.skillCollection?.stream()?.map { it.id }?.collect(Collectors.toList())
            ob.uO = com.service_exchange.api_services.dao.dto.UserInfo(t.madeBy?.name, t.id, t.image)
            ob.numberOfTransaction = t.transactionInfoCollection?.stream()?.filter({ t ->
                t.state == TransactionInfo.COMPLETED_STATE
                        || t.state == TransactionInfo.LATE_STATE
            })?.count()?.toInt()
            ob.rating = t.transactionInfoCollection?.stream()?.filter { t -> t.state == TransactionInfo.COMPLETED_STATE || t.state == TransactionInfo.LATE_STATE }
                    ?.mapToDouble { t ->
                        t.reviewCollection?.stream()
                                ?.mapToDouble { it.rating?.toDouble() ?: 0.0 }?.average()?.orElse(0.0) ?: 0.0
                    }?.average()?.orElse(0.0)
            ob.expectDur = t.duration?.time
            ob
        }

fun ServiceDTO.convertServie(skillInterface: SkillInterface, userInterface: UserInterFace): Service =
        this.let { t ->
            val ob = AppFactory.mapToDto(t, Service::class.java)
            ob.skillCollection = t.skillList?.stream()?.map { skillInterface.getSkillById(it) }
                    ?.collect(Collectors.toList()) ?: kotlin.collections.emptyList()
            ob.madeBy = userInterface.getUser(t.id)
            t.expectDur?.let { l -> ob.duration = java.util.Date(l) }
            ob
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

fun Message.convertToTransaction() =
        AppFactory.mapToDto(this, MessageTransactionDto::class.java)

fun Message.convertToComplain() =
        AppFactory.mapToDto(this, MessageComplaintDto::class.java)

fun SkillDTO.convertToSkill() =
        AppFactory.mapToDto(this, Skill::class.java)

fun AdminMain.convertToAdmin() =
        AppFactory.mapToDto(this, AdminTable::class.java)
