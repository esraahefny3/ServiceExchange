package com.service_exchange.api_services.KotlinUtal

import com.service_exchange.api_services.bussinessdaodelegates.user.UserStaticsGetter
import com.service_exchange.api_services.bussinessdaodelegates.user.convert
import com.service_exchange.api_services.dao.dto.*
import com.service_exchange.api_services.dao.skill.SkillInterface
import com.service_exchange.api_services.dao.transaction.TransactionDto
import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.api_services.factories.AppFactory
import com.service_exchange.entities.*
import java.util.stream.Collectors

fun Collection<Skill>.getList(): List<SkillInfo> =
        stream().map { it.convertSkillInfo() }?.collect(Collectors.toList()) ?: emptyList()

fun Collection<TransactionInfo>.count(): Int? =
        stream().filter({
            it.state == TransactionInfo.COMPLETED_STATE
                    || it.state == TransactionInfo.LATE_STATE
        })?.count()?.toInt()

fun Collection<TransactionInfo>.avgRating(): Double? =
        stream().filter { it.state == TransactionInfo.COMPLETED_STATE || it.state == TransactionInfo.LATE_STATE }
                ?.mapToDouble {

                    it.reviewCollection?.stream()
                            ?.mapToDouble { it.rating?.toDouble() ?: 0.0 }?.average()?.orElse(0.0) ?: 0.0
                }?.average()?.orElse(0.0)

fun Collection<TransactionInfo>.reviewList(): MutableList<ReviewDTO> {
    val list = LinkedHashSet<ReviewDTO>()

    stream().filter { it.state == TransactionInfo.LATE_STATE || it.state == TransactionInfo.COMPLETED_STATE || it.state == TransactionInfo.COMPLETED_APPROVED_STATE }
            .forEach { it.reviewCollection?.stream()?.forEach { list += it.convertReview() } }


    return list.toMutableList()
}


fun Service.convertServie(): ServiceDTO =
        this.let { t ->
            val ob = AppFactory.mapToDto(t, ServiceDTO::class.java)
            ob.available = t.available
            ob.skillList = t.skillCollection?.stream()?.map { it.id }?.collect(Collectors.toList())
            ob.uO = com.service_exchange.api_services.dao.dto.UserInfo(t.madeBy?.name, t.madeBy?.id, t.madeBy?.image)
            ob.numberOfTransaction = t.transactionInfoCollection?.count()
            ob.rating = t.transactionInfoCollection?.avgRating()
            ob.expectDur = t.duration?.toLong()
            ob.revList = t.transactionInfoCollection?.reviewList()


            ob
        }

fun UserTable.convertUser(): UserDTO {
    val userdto = AppFactory.mapToDto(this, UserDTO::class.java)
    userdto.bD = birthDate?.time
    userdto.userEmailCollection = this.userEmailCollection?.stream()?.map { it.userEmailPK.email }?.collect(Collectors.toList())
    userdto.UserTelephone = this.userTelephoneCollection?.stream()?.map { it.userTelephonePK.telephone }?.collect(Collectors.toList())
    userdto.uSkills = this.skillCollection?.stream()?.map { it.convertSkill() }?.collect(Collectors.toList())
    userdto.joinedDate = signUpDate?.time
    return userdto
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

fun ServiceDTO.convertToServcieWEB(): ServicesWEB =
        ServicesWEB().apply {
            serviceId = this@convertToServcieWEB.id
            serviceImg = this@convertToServcieWEB.image
            serviceName = this@convertToServcieWEB.name
            numOfReviews = this@convertToServcieWEB.revList?.size ?: 0
            numOfPoints = this@convertToServcieWEB.price
        }

fun TransactionInfo.convert(): TransactionDto =
        AppFactory.mapToDto(this, TransactionDto::class.java).apply {
            this.serviceId = this@convert.serviceId?.id
            this.serviceName = this@convert.serviceId?.name
            this.serviceDescription = this@convert.serviceId?.description
            this.setsByUser(this@convert.startedBy?.id)
            this.otherUserImage = this@convert.startedBy?.image
            this.otherUserName = this@convert.startedBy?.name

        }


fun Skill.convertSkill(): SkillDTO {

    val skill = AppFactory.mapToDto(this, SkillDTO::class.java)
    skill.rating = this.reviewSkillCollection.stream().mapToDouble { it.rating.toDouble() }.average().orElse(0.0)
    return skill
}

fun Skill.convertSkillInfo(): SkillInfo {


    return SkillInfo(id, description, name)
}

fun Skill.convertSkillAlone(): SkillDTO {

    val skill = AppFactory.mapToDto(this, SkillDTO::class.java)
    if (parentSkillId != null)
        skill.psId = parentSkillId.id
    skill.children = skillCollection.stream().map { it.convertSkillAlone() }.collect(Collectors.toList()).take(3)
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

fun Service.convertToServiceHoda() =
        AppFactory.mapToDto(this, ServiceHoda::class.java).apply {
            available = this@convertToServiceHoda.available
            catagory = this@convertToServiceHoda.skillCollection?.getList()
            userINf = com.service_exchange.api_services.dao.dto.UserInfo(madeBy?.name, madeBy?.id, madeBy?.image)
            CompletedtransActionCout = transactionInfoCollection?.count()
            ratingAvg = transactionInfoCollection?.avgRating()
            RevList = transactionInfoCollection?.reviewList()
            reviewCount = RevList?.size
        }

fun UserTable.convetToUserDataWeb(userStatic: UserStaticsGetter): UserDataWEB =
        UserDataWEB().apply {
            this.UserBio = bio
            this.UserDesc = description
            this.UserEducation = educationCollection?.stream()?.map { it.convert() }?.collect(Collectors.toList())
            this.UserEmails = userEmailCollection?.stream()?.map { it.userEmailPK?.email }?.collect(Collectors.toList())?.filterNotNull()
            this.UserImg = image
            this.UserLocation = address
            this.UserName = name
            this.UserSkills = skillCollection?.stream()?.map { it.convertSkillInfo() }?.collect(Collectors.toList())
                    ?.toList()
            this.avgResponseTime = userStatic.getResponceRate(id)
            this.numOfReviews = transactionInfoCollection?.reviewList()?.size ?: 0
            this.level = userStatic.getUserLevel(id!!)
            this.userId = id


        }
