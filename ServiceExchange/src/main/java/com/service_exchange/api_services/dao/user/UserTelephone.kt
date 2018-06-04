package com.service_exchange.api_services.dao.user

import com.service_exchange.entities.UserTelephone
import com.service_exchange.entities.UserTelephonePK
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Repository
interface UserTelephoneData : PagingAndSortingRepository<UserTelephone, UserTelephonePK>

interface UserTelephoneInterface {
    fun addTelephoneToUser(telephone: String?, userId: Int?): Boolean
    fun removeTelephoneFromUser(telephone: String?, userId: Int?): Boolean
}

@Service
private open class UserTelephoneImpl : UserTelephoneInterface {
    @Autowired
    lateinit var userInterFace: UserInterFace
    @Autowired
    lateinit var userTelephoneData: UserTelephoneData


    override fun addTelephoneToUser(telephone: String?, userId: Int?): Boolean {
        return if (telephone != null && userId != null) {
            val user = userInterFace.getUser(userId)
            if (user != null) {
                val newTelephone = UserTelephone()
                newTelephone.userTable = user
                val pk = UserTelephonePK()
                pk.telephone = telephone
                pk.userId = userId
                newTelephone.userTelephonePK = pk
                userTelephoneData.save(newTelephone)

                true
            } else
                false
        } else
            false
    }

    override fun removeTelephoneFromUser(telephone: String?, userId: Int?): Boolean =
            if (telephone != null && userId != null) {
                val pk = UserTelephonePK()
                pk.userId = userId
                pk.telephone = telephone
                userTelephoneData.deleteById(pk)
                true
            } else false


}