package com.service_exchange.api_services.dao.user

import com.service_exchange.entities.Education
import com.service_exchange.entities.EducationPK
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface EdcationData : PagingAndSortingRepository<Education, EducationPK>

interface UserEducationInterface {
    fun addEducation(newEducation: Education, userId: Int): Education?
    fun removeEducation(educationId: Int, userId: Int): Boolean
    fun modifieEducation(education: Education?, userId: Int): Education?
    fun getEducation(educationId: Int, userId: Int): Education?
}

private class UserEducationImpl : UserEducationInterface {

    @Autowired
    lateinit var edcationData: EdcationData
    @Autowired
    lateinit var userInterFace: UserInterFace

    override fun addEducation(newEducation: Education, userId: Int): Education? {
        val userTable: UserTable? = userInterFace.getUser(userId)
        return if (userTable != null) {
            val edcatioPK = EducationPK()
            edcatioPK.userId = userId
            newEducation.educationPK = edcatioPK
            newEducation.userTable = userTable
            edcationData.save(newEducation)
        } else null
    }

    override fun removeEducation(educationId: Int, userId: Int): Boolean {
        val userTable: UserTable? = userInterFace.getUser(userId)
        return if (userTable != null) {
            val edcatioPK = EducationPK()
            edcatioPK.userId = userId
            edcatioPK.id = educationId
            val education = edcationData.findById(edcatioPK)
            if (education.isPresent) {
                edcationData.delete(education.get())
                true
            } else false

        } else false
    }

    override fun modifieEducation(education: Education?, userId: Int): Education? =
            if (education != null)
                edcationData.save(education)
            else null

    override fun getEducation(educationId: Int, userId: Int): Education? {
        val educationPK = EducationPK()
        educationPK.id = educationId
        educationPK.userId = userId
        val education = edcationData.findById(educationPK)
        return if (education.isPresent) {
            education.get()
        } else null
    }

}
