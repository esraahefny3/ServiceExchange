package com.service_exchange.api_services.dao.dto

import java.util.*

class AdminMain {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var image: String? = null
    var telephones: List<String>? = null


}

class AdminChallange {
    var id: Int? = null
    var name: String? = null
    var description: String? = null
    var reward: Int? = null
    var period: Int? = null
    val userInChallange: List<AdminUserId>? = null


}

class AdminUserId {
    var id: Int? = null


}

class AdminComplaint {
    var id: Int? = null
    var state: String? = null
    var description: String? = null
    var date: Date? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdminComplaint

        if (id != other.id) return false
        if (state != other.state) return false
        if (description != other.description) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (state?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        return result
    }

}

class AdminNotification {
    var id: Int? = null
    var body: String? = null
    var notifecationDate: Date? = null
    var sendToUser: List<AdminUserId>? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdminNotification

        if (id != other.id) return false
        if (body != other.body) return false
        if (notifecationDate != other.notifecationDate) return false
        if (sendToUser != other.sendToUser) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (body?.hashCode() ?: 0)
        result = 31 * result + (notifecationDate?.hashCode() ?: 0)
        result = 31 * result + (sendToUser?.hashCode() ?: 0)
        return result
    }

}

class ChallangeDto {

    var id: Int? = null
    var name: String? = null
    var description: String? = null
    var reward: Int? = null
    var period: Int? = null
    var userList: List<UserDTO>? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChallangeDto

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (reward != other.reward) return false
        if (period != other.period) return false
        if (userList != other.userList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (reward ?: 0)
        result = 31 * result + (period ?: 0)
        result = 31 * result + (userList?.hashCode() ?: 0)
        return result
    }

}

class UserDTO {
    var id: Int? = null
    var name: String? = null
    var image: String? = null
    var status: String? = null
    var birthDate: String? = null
    var accountType: String? = null
    var accountId: String? = null
    var userEmailCollection: List<String>? = null
    var UserTelephone: List<String>? = null
    var isFrist: Boolean? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserDTO

        if (id != other.id) return false
        if (name != other.name) return false
        if (image != other.image) return false
        if (status != other.status) return false
        if (birthDate != other.birthDate) return false
        if (accountType != other.accountType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        result = 31 * result + (birthDate?.hashCode() ?: 0)
        result = 31 * result + (accountType?.hashCode() ?: 0)
        return result
    }

}

class ServiceDTO {
    var id: Int? = null

    var name: String? = null

    var image: String? = null

    var price: Int? = null

    var type: String? = null

    var description: String? = null

    var isAvailable: Int? = null

    var skillList: List<Int>? = null

    var userIdOwner: Int? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ServiceDTO

        if (id != other.id) return false
        if (name != other.name) return false
        if (image != other.image) return false
        if (price != other.price) return false
        if (type != other.type) return false
        if (description != other.description) return false
        if (isAvailable != other.isAvailable) return false
        if (skillList != other.skillList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (price ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (isAvailable ?: 0)
        result = 31 * result + (skillList?.hashCode() ?: 0)
        return result
    }

}

class SkillDTO {
    var id: Int? = null

    var name: String? = null

    var description: String? = null

    var isVerified: Int? = null

    var reivewsList: List<ReViewDTO>? = null

    var psId: Int? = null

}

class ReViewDTO {
    var id: Int? = null

    var comment: String? = null

    var rating: Int? = null

    var transactionId: Int? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReViewDTO

        if (id != other.id) return false
        if (comment != other.comment) return false
        if (rating != other.rating) return false
        if (transactionId != other.transactionId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (comment?.hashCode() ?: 0)
        result = 31 * result + (rating ?: 0)
        result = 31 * result + (transactionId ?: 0)
        return result
    }

}

class EdcationDTO {
    var degree: String? = null

    var major: String? = null

    var grade: String? = null


    var startDate: Long? = null


    var endDate: Long? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EdcationDTO

        if (degree != other.degree) return false
        if (major != other.major) return false
        if (grade != other.grade) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = degree?.hashCode() ?: 0
        result = 31 * result + (major?.hashCode() ?: 0)
        result = 31 * result + (grade?.hashCode() ?: 0)
        result = 31 * result + (startDate?.hashCode() ?: 0)
        result = 31 * result + (endDate?.hashCode() ?: 0)
        return result
    }




}

class BadgeDto{
     var id: Int? = null
     var name: String? = null
     var image: String? = null
     var description: String? = null
     var timeNeeded: String? = null
     var type: String? = null
     var addedByAdminEmail: String? = null
}

class ComplaintDto{
     var id: Int? = null
     var state: String? = null
     var description: String? = null
     var date: Long? = null
     var reviewedByAdmin: String? = null
     var transactionId: Int? = null
     var userId: Int? = null
}

class MessageComplaintDto {

     var id: Int? = null
     var text: String? = null
     var attachment: String? = null
     var date: Long? = null
     var complaintId: Int? = null
     var receiverId: Int? = null
     var senderId: Int? = null
}

class MessagePrivateDto {

     var id: Int? = null
     var text: String? = null
     var attachment: String? = null
     var date: Long? = null
     var receiverId: Int? = null
     var senderId: Int? = null
}

class MessageTransactionDto {
     var id: Int? = null
     var text: String? = null
     var attachment: String? = null
     var date: Long? = null
     var transactionId: Int? = null
     var receiverId: Int? = null
     var senderId: Int? = null
}

class TransactionChatDto {
     var transactionId: Int? = null
     var transactionChatMessagesList: List<MessageTransactionDto>? = null
}