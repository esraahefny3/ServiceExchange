package com.service_exchange.api_services.dao.dto

import java.util.*

class AdminMain {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var image: String? = null
    var telephones: List<String>? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdminMain

        if (name != other.name) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (image != other.image) return false
        if (telephones != other.telephones) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (password?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (telephones?.hashCode() ?: 0)
        return result
    }

}

class AdminChallange {
    var id: Int? = null
    var name: String? = null
    var description: String? = null
    var reward: Int? = null
    var period: Int? = null
    val userInChallange: List<AdminUserId>? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdminChallange

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (reward != other.reward) return false
        if (period != other.period) return false
        if (userInChallange != other.userInChallange) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (reward ?: 0)
        result = 31 * result + (period ?: 0)
        result = 31 * result + (userInChallange?.hashCode() ?: 0)
        return result
    }

}

class AdminUserId {
    var id: Int? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdminUserId

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id ?: 0
    }

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