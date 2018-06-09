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


}

class AdminNotification {
    var id: Int? = null
    var body: String? = null
    var notifecationDate: Date? = null
    var sendToUser: List<AdminUserId>? = null


}

class ChallangeDto {

    var id: Int? = null
    var name: String? = null
    var description: String? = null
    var reward: Int? = null
    var period: Int? = null
    var userList: List<UserDTO>? = null


}

class UserDTO {
    var id: Int? = null
    var name: String? = null
    var image: String? = null
    var status: String? = null
    var bD: Long? = null
    var accountType: String? = null
    var accountId: String? = null
    var balance: Int? = null
    var userEmailCollection: List<String>? = null
    var UserTelephone: List<String>? = null
    var isFrist: Boolean? = null
    var uSkills: List<SkillDTO>? = null

}

class UserInfo(val userName: String?, val id: Int?, val image: String?) {

}

class ServiceDTO {
    var id: Int? = null

    var name: String? = null

    var image: String? = null

    var price: Int? = null

    var type: String? = null

    var description: String? = null

    var isAvailable: String? = null

    var skillList: List<Int>? = null

    var uO: UserInfo? = null
    // number of completed transaction
    var numberOfTransaction: Int? = null
    //rating of service 0 if no one used it
    var rating: Double? = null


}

class SkillDTO {
    var id: Int? = null

    var name: String? = null

    var description: String? = null

    var isVerified: Int? = null

    var rating: Double? = null

    var reivewsList: List<ReViewDTO>? = null

    var children: List<SkillDTO>? = null

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

class BadgeDto {
    var id: Int? = null
    var name: String? = null
    var image: String? = null
    var description: String? = null
    var timeNeeded: String? = null
    var type: String? = null
    var addedByAdminEmail: String? = null
    var nL: Int? = null
}

class ComplaintDto {
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
    var userReadIt: Short? = null
    var readDate: Date? = null
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
    var userReadIt: Short? = null
    var readDate: Date? = null
}

class TransactionChatDto {
    var transactionId: Int? = null
    var transactionChatMessagesList: List<MessageTransactionDto>? = null
}

class ActiveOrder(var orderCount: Int, var ordersValue: Int)

class EarningListObject(val id: Int?, val name: String?, val endDate: Long?, val price: Int?)

class UserStatics {

    var responseTime: Double? = null
    var responseRate: Double? = null
    var orderCompletion: Double? = null
    var onTimeDelivery: Double? = null
    var feedBackRate: Double? = null
    var nextLevelDescription: String? = null
    var currentLevel: String? = null
    var earningInThisMounth: Double? = null
    var activeOrder: ActiveOrder? = null
    var presonalBalance: Int? = null
    var avgSellIng: Double? = null
    var numberOfUnreadedMessage: Int? = null
    var allUserPoint: Int? = null

}