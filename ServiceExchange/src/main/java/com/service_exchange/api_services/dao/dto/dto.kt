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
    // var UserTelephone: List<String>? = null
    var isFrist: Boolean? = null
    var uSkills: List<SkillDTO>? = null
    var bio: String? = null
    var address: String? = null
    var joinedDate: Long? = null
    var descrption: String? = null
    override fun toString(): String {
        return "UserDTO(id=$id, name=$name, image=$image, status=$status, bD=$bD, accountType=$accountType, accountId=$accountId, balance=$balance, userEmailCollection=$userEmailCollection, isFrist=$isFrist, uSkills=$uSkills, bio=$bio, address=$address, joinedDate=$joinedDate, descrption=$descrption)"
    }


}


class UserInfo(val userName: String?, val id: Int?, val image: String?) {
    constructor() : this(null, null, null) {

    }
}

data class ServiceDTO(var id: Int?, var name: String?, var image: String?, var price: Int?, var type: String?,
                      var description: String?, var available: String?, var skillList: List<Int>?, var expectDur: Long?,
                      var duration: Long?, var uO: UserInfo?, var numberOfTransaction: Int?, var rating: Double?,
                      var revList: MutableList<ReviewDTO>?) {
    constructor() : this(null, null, null, null, null, null, null, null, null,
            null, null, null, null, null)


}

class SkillDTO {
    var id: Int? = null

    var name: String? = null

    var description: String? = null

    var isVerified: Int? = null

    var rating: Double? = null

    var reivewsList: List<ReviewDTO>? = null

    var children: List<SkillDTO>? = null

    var psId: Int? = null

}

class ReviewDTO {
    var id: Int? = null

    var comment: String? = null

    var rating: Int? = null

    var transactionId: Int? = null

    var userInfo: UserInfo? = null

    var madeBy: Int? = null

    var reviewDate: Long? = null

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
    var nextLevelId: Int? = null
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


class MessageGeneralDto {
    var id: Int? = null
    var text: String? = null
    var attachment: String? = null
    var date: Long? = null
    var transactionId: Int? = null
    var receiverId: Int? = null
    var senderId: Int? = null
    var userReadIt: Short? = null
    var readDate: Long? = null
    var complaintId: Int? = null
}

class TransactionChatDto {
    var transactionId: Int? = null

    var userId: Int? = null
    var userName: String? = null
    var userImage: String? = null
    var userStatus: String? = null
    var ServiceId: Int? = null
    var serviceName: String? = null

    var transactionChatMessagesList: List<MessageGeneralDto>? = null
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

class NotificationDto {

    var id: Int? = null
    var body: String? = null
    var notifecationDate: Date? = null
    var sentBy: String? = null
    var type: String? = null
    var type_id: Int? = null

}

class UserAdminInfo {
    var name: String? = null
    var state: String? = null
    var image: String? = null
    var reivews: Int? = null
    var requests: Int? = null
    var services: Int? = null
    var balance: Int? = null
    var pointSpend: Int? = null
    var PointEarned: Int? = null
}

class ServiceAdminInfo {
    var id: Int? = null
    var name: String? = null
    var price: Int? = null
    var reviewsCount: Int? = null
    var ownerName: String? = null
    var ownerImage: String? = null
    var startedDated: Long? = null
    var serviceImage: String? = null
    var category: String? = null

}


class ComplaintStatics {
    var acceptedCount: Int? = null
    var rejectedCount: Int? = null
    var onReviewCount: Int? = null
    var completedCount: Int? = null
    var notReveiwCount: Int? = null
}

class UserStatices {
    var onlineCount: Int? = null
    var offlineCount: Int? = null
    var busyCount: Int? = null
    var userCount: Int? = null
}

class SkillStatistics {
    var mainCount: Int? = null
    var totalSkillCount: Int? = null
}

class ServiceStatices {
    var offeredCount: Int? = null
    var reqCount: Int? = null
    var pausedCount: Int? = null
}

class TransactionStatices {
    var activeCount: Int? = null
    var waitingCount: Int? = null
    var totalPointCount: Int? = null
    var completedCount: Int? = null
    var lateCompletedCount: Int? = null
    var caneledCount: Int? = null
}

data class TransactionEslam(var userInfo: UserInfo?, var descrption: String?
                            , var numberOfDays: Int?, var date: Long?, var price: Int?
                            , var duration: Int?, var serviceId: Int?) {
    constructor() : this(null, null, null, null, null, null, null)
}

data class ServiceHoda(var id: Int?, var name: String?, var userINf: UserInfo?,
                       var price: Int?, var catagory: List<SkillInfo>?, var reviewCount: Int?,
                       var ratingAvg: Double?, var RevList: List<ReviewDTO>?, var CompletedtransActionCout: Int?) {
    constructor() : this(null, null, null, null, null, null, null, null, null)
}

class HodaProfile(val lastActiveService: ServiceHoda?, val lastPousedServiceHoda: ServiceHoda?,
                  val lastReqActive: ServiceHoda?, val lastCompletedReq: ServiceHoda?)

data class SkillInfo(var id: Int?, var descrption: String?, var name: String?) {
    constructor() : this(null, null, null)
}

open class ServicesWEB(var serviceId: Int?, var serviceImg: String?, var serviceName: String?, var numOfPoints: Int?, var numOfReviews: Int?, var serviceOwnerImage: String?) {
    constructor() : this(null, null, null, null, null, null)
}

class ServiceDetailWeb : ServicesWEB(null, null, null, null, null, null) {
    var serviceOwnerId: Int? = null
    var allreview: List<ReviewWEB>? = null

}
data class RequestsWEB(var requestId: Int?, var userImg: String?, var userId: Int?, var requestTitle: String?, var numOfPointsPay: Int?, var requestDesc: String?) {
    constructor() : this(null, null, null, null, null, null)
}

data class UserWEB(var userId: Int?, var userImg: String?, var userName: String?, var numOfReviews: Int?, var numOfPoints: Int?
                   , var userLocation: String?, var userBio: String?) {
    constructor() : this(null, null, null, null, null, null, null)
}

open class UserDataWEB {
    var userId: Int? = null
    var UserImg: String? = null
    var UserName: String? = null
    var numOfReviews: Int? = null
    var UserBio: String? = null
    var UserLocation: String? = null
    var avgResponseTime: Double? = null
    var recentDelievery: String? = null
    var level: String? = null
    var status: String? = null
    var UserDesc: String? = null
    var UserEmails: List<String>? = null
    var UserSkills: List<SkillInfo>? = null
    var UserEducation: List<EdcationDTO>? = null
}

class UserDataWEBInDetails : UserDataWEB() {
    var currentBalance: Int? = null
    var onHoldBalance: Int? = null
    var availablealance: Int? = null
    var transactionHistory: List<TransActionHistory>? = null
}

class TransActionHistory {
    var date: Long? = null
    var description: String? = null
    var amount: Int? = null
    var balance: Int? = null
}



class UserServiceData {
    var user_id: Int? = null
    var UserServicesActive: List<ServicesWEB>? = null
    var UserServicesPaused: List<ServicesWEB>? = null
    var UserRequestActive: List<RequestsWEB>? = null
    var UserRequestPaused: List<RequestsWEB>? = null
    var UserRequestCompleted: List<RequestsWEB>? = null
    var userServicesReviews: List<ReviewWEB>? = null
}

class ReviewWEB {
    var personImg: String? = null
    var personName: String? = null
    var serviceReview: Double? = null
    var time: Long? = null
    var personComment: String? = null
}

class MyRequerstWeB {
    var UserRequestActive: List<RequestsWEB>? = null
    var UserRequestPaused: List<RequestsWEB>? = null
    var UserRequestCompleted: List<RequestsWEB>? = null

}