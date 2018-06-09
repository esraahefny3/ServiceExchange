package com.service_exchange.api_services.dao.user

import com.service_exchange.entities.UserEmail
import com.service_exchange.entities.UserEmailPK
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
interface UserEmailData : PagingAndSortingRepository<UserEmail, UserEmailPK> {
    fun findByUserEmailPK_Email(email: String): UserEmail?
}

interface UserEmailInterface {
    fun addEmail(email: String?, userId: Int?): Boolean
    fun removeEmail(email: String?, userId: Int?): Boolean

}

@Component
private class UserEmailImpl : UserEmailInterface {
    @Autowired
    lateinit var userInterFace: UserInterFace
    @Autowired
    lateinit var userEmailData: UserEmailData


    override fun addEmail(email: String?, userId: Int?): Boolean {
        return if (userId != null) {
            val user: UserTable? = userInterFace.getUser(userId)
            if (user != null && email != null) {
                val newEmail = UserEmail()
                val emailPK = UserEmailPK()
                emailPK.email = email
                emailPK.userId = user.id ?: 0
                newEmail.userTable = user
                newEmail.userEmailPK = emailPK
                userEmailData.save(newEmail)

                return true
            }
            false
        } else false

    }

    override fun removeEmail(email: String?, userId: Int?): Boolean {
        val user: UserTable? = userInterFace.getUser(userId)
        val item = user?.userEmailCollection?.stream()?.filter { t -> t.userEmailPK.email == email }?.findFirst()
        return if (item != null && item.isPresent) {
            userEmailData.delete(item.get())
            true
        } else false

    }

}