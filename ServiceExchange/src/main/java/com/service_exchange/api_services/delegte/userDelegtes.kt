package com.service_exchange.api_services.delegte

import com.service_exchange.api_services.dao.dto.EdcationDTO
import com.service_exchange.api_services.dao.dto.ServiceDTO
import com.service_exchange.api_services.dao.dto.SkillDTO
import com.service_exchange.api_services.dao.dto.UserDTO
import com.service_exchange.api_services.dao.user.UserEducationInterface
import com.service_exchange.api_services.dao.user.UserEmailInterface
import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.api_services.dao.user.UserTelephoneInterface
import com.service_exchange.api_services.dao.user.userService.UserServicesInterFace
import com.service_exchange.api_services.dao.user.userSkill.UserSkillInterFace
import com.service_exchange.api_services.factories.AppFactory
import com.service_exchange.entities.Education
import com.service_exchange.entities.Service
import com.service_exchange.entities.Skill
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors


interface UserDataGet {
    fun loginOrSignUp(user: UserDTO?): UserDTO?
    fun getUserSkill(userId: Int?): List<SkillDTO>
    fun getUserEdcation(userId: Int?): List<EdcationDTO>
    fun getUserServices(UserId: Int?): List<ServiceDTO>

}

@Component
private class UserDataGetImpl : UserDataGet {
    @Autowired
    lateinit var userInterface: UserInterFace
    @Autowired
    lateinit var userSkillInterFace: UserSkillInterFace
    @Autowired
    lateinit var userService: UserServicesInterFace

    override fun loginOrSignUp(user: UserDTO?): UserDTO? =
            if (user != null) {
                val userDTO = AppFactory.mapToDto(user, UserTable::class.java)
                val retVal = userInterface.createUser(userDTO)
                if (retVal != null) {
                    val userdto = AppFactory.mapToDto(retVal, UserDTO::class.java)
                    userdto.userEmailCollection = retVal.userEmailCollection.stream().map { it.userEmailPK.email }.collect(Collectors.toList())
                    userdto.UserTelephone = retVal.userTelephoneCollection.stream().map { it.userTelephonePK.telephone }.collect(Collectors.toList())
                    userdto
                } else null

            } else null


    override fun getUserSkill(userId: Int?): List<SkillDTO> {
        if (userId != null) {

            val userSkill = userSkillInterFace.getUserSkill(userId)?.stream()?.map { AppFactory.mapToDto(it, SkillDTO::class.java) }?.collect(Collectors.toList())
                    ?: emptyList<SkillDTO>()


            return userSkill
        }
        return emptyList()
    }

    override fun getUserEdcation(userId: Int?): List<EdcationDTO> {
        return userInterface.getEducation(userId)?.stream()?.map { AppFactory.mapToDto(it, EdcationDTO::class.java) }?.collect(Collectors.toList())
                ?: emptyList()
    }

    override fun getUserServices(userId: Int?): List<ServiceDTO> {
        return userService.getUserServices(userId)?.stream()?.map { AppFactory.mapToDto(it, ServiceDTO::class.java) }?.collect(Collectors.toList())
                ?: emptyList()
    }

}

interface UserDataSet {
    fun addEmailToUser(email: String?, userId: Int?): Boolean
    fun addEdcationToUser(edcationDTO: EdcationDTO?, userId: Int?): Boolean
    fun addSkillToUser(skillDTO: SkillDTO?, userId: Int?): Boolean
    fun addTelephonToUser(telephone: String?, userId: Int?): Boolean
    fun addServiceToUser(serviceDTO: ServiceDTO?): Boolean
}

@Component
private class UserDataSetImol : UserDataSet {
    @Autowired
    lateinit var userSkillInterFace: UserSkillInterFace
    @Autowired
    lateinit var userService: UserServicesInterFace
    @Autowired
    lateinit var userEmail: UserEmailInterface
    @Autowired
    lateinit var userEdcation: UserEducationInterface
    @Autowired
    lateinit var userTelephone: UserTelephoneInterface

    override fun addEmailToUser(email: String?, userId: Int?): Boolean =
            if (userId != null && email != null) {
                userEmail.addEmail(email, userId)
                true
            } else {
                false
            }


    override fun addEdcationToUser(edcationDTO: EdcationDTO?, userId: Int?): Boolean =
            if (userId != null && edcationDTO != null) {
                val edcation = AppFactory.mapToDto(edcationDTO, Education::class.java)
                userEdcation.addEducation(edcation, userId)
                true
            } else {
                false
            }

    override fun addSkillToUser(skillDTO: SkillDTO?, userId: Int?): Boolean =
            if (userId != null && skillDTO != null) {
                val skill = AppFactory.mapToDto(skillDTO, Skill::class.java)
                userSkillInterFace.addSkill(skill, userId)
                true
            } else {
                false
            }

    override fun addTelephonToUser(telephone: String?, userId: Int?): Boolean {
        return userTelephone.addTelephoneToUser(telephone, userId)
    }

    override fun addServiceToUser(serviceDTO: ServiceDTO?): Boolean {
        return if (serviceDTO != null) {
            val service = AppFactory.mapToDto(serviceDTO, Service::class.java)
            userService.addServiceToUser(serviceDTO.userIdOwner, service)
        } else false

    }

}

interface UserDataDelete {
    fun removeEmailFormUser(email: String?, userId: Int?): Boolean
    fun removeEdcationFromUser(edcationId: Int?, userId: Int?): Boolean
    fun removeSkillFormUser(skillId: Int?, userId: Int?): Boolean
    fun removeTelePhoneFormUser(telephone: String?, userId: Int?): Boolean
    fun removeServiceToUser(userId: Int?, serviceId: Int?, forced: Boolean?): Boolean
}

@Component
private class UserDataDeletImpel : UserDataDelete {
    @Autowired
    lateinit var userSkillInterFace: UserSkillInterFace
    @Autowired
    lateinit var userService: UserServicesInterFace
    @Autowired
    lateinit var userEmail: UserEmailInterface
    @Autowired
    lateinit var userEdcation: UserEducationInterface
    @Autowired
    lateinit var userTelephone: UserTelephoneInterface

    override fun removeEmailFormUser(email: String?, userId: Int?): Boolean =
            if (email != null && userId != null) {
                userEmail.removeEmail(email, userId)

            } else false


    override fun removeEdcationFromUser(edcationId: Int?, userId: Int?): Boolean =
            if (edcationId != null && userId != null) {
                userEdcation.removeEducation(educationId = edcationId, userId = userId)

            } else false

    override fun removeSkillFormUser(skillId: Int?, userId: Int?): Boolean =
            if (skillId != null && userId != null) {
                userSkillInterFace.deleteSkill(skillId, userId)

            } else false

    override fun removeTelePhoneFormUser(telephone: String?, userId: Int?): Boolean =
            userTelephone.removeTelephoneFromUser(telephone, userId)


    override fun removeServiceToUser(userId: Int?, serviceId: Int?, forced: Boolean?): Boolean =
            userService.removeServiceForUser(userId, serviceId, forced)

}
