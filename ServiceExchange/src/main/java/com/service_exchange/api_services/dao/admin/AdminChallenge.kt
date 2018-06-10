package com.service_exchange.api_services.dao.admin

import com.service_exchange.api_services.dao.challanges.ChallangeDao
import com.service_exchange.api_services.dao.challanges.ChallangeInterFace
import com.service_exchange.entities.AdminTable
import com.service_exchange.entities.Challenge
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.stream.Collectors

interface AdminChallengeInterface {
    fun addChallange(challange: Challenge?, adminId: String): Challenge?
    fun suspendChallange(challengeId: Int): Boolean
    fun resumeChallange(challengeId: Int): Boolean
    fun getChallangeCreatedByAdmin(adminId: String): List<Challenge>

}

@Component
private class AdminChallengeImpl : AdminChallengeInterface {
    @Autowired
    lateinit var admindata: AdminInterface
    @Autowired
    @Qualifier("challangeInterFace")
    lateinit var challangeInterface: ChallangeInterFace

    override fun getChallangeCreatedByAdmin(adminId: String): List<Challenge> =
            admindata.getAdmin(adminId)?.challengeCollection?.stream()?.collect(Collectors.toList()) ?: mutableListOf()


    @Autowired
    lateinit var challengeDao: ChallangeDao

    override fun addChallange(challange: Challenge?, adminId: String): Challenge? {
        val admin: AdminTable? = admindata.getAdmin(adminId)
        challange?.addedBy = admin
        return if (admin != null && challange != null)
            challengeDao.createChallange(challange)
        else null
    }


    override fun suspendChallange(challengeId: Int): Boolean {
        challangeInterface.getChallange(challengeId)?.let { challenge ->
            challenge.isSuspended = Challenge.SUSBENDED
            return true
        }
        return false
    }

    override fun resumeChallange(challengeId: Int): Boolean {
        challangeInterface.getChallange(challengeId)?.let { challenge ->
            challenge.isSuspended = Challenge.RESUMED
            return true
        }
        return false
    }

}