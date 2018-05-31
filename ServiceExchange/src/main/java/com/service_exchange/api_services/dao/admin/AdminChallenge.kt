package com.service_exchange.api_services.dao.admin

import com.service_exchange.api_services.dao.challanges.ChallangeDao
import com.service_exchange.entities.AdminTable
import com.service_exchange.entities.Challenge
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

interface AdminChallengeInterface {
    fun addChallange(challange: Challenge?, adminId: Int): Challenge?
    fun suspendChallange(challengeId: Int): Boolean
    fun getChallangeCreatedByAdmin(adminId: Int): List<Challenge>

}

@Component
private class AdminChallengeImpl : AdminChallengeInterface {
    @Autowired
    lateinit var admindata: AdminInterface

    override fun getChallangeCreatedByAdmin(adminId: Int): List<Challenge> =
            admindata.getAdmin(adminId)?.challengeCollection?.stream()?.collect(Collectors.toList()) ?: mutableListOf()


    @Autowired
    lateinit var challengeDao: ChallangeDao

    override fun addChallange(challange: Challenge?, adminId: Int): Challenge? {
        val admin: AdminTable? = admindata.getAdmin(adminId)
        challange?.addedBy = admin
        return if (admin != null && challange != null)
            challengeDao.createChallange(challange)
        else null
    }


    override fun suspendChallange(challengeId: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}