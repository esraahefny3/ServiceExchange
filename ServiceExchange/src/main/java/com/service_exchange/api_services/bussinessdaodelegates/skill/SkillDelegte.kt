package com.service_exchange.api_services.bussinessdaodelegates.skill

import com.service_exchange.api_services.bussinessdaodelegates.service.convertServie
import com.service_exchange.api_services.bussinessdaodelegates.user.convertUser
import com.service_exchange.api_services.dao.dto.ServiceDTO
import com.service_exchange.api_services.dao.dto.SkillDTO
import com.service_exchange.api_services.dao.dto.UserDTO
import com.service_exchange.api_services.dao.skill.SkillInterface
import com.service_exchange.api_services.factories.AppFactory
import com.service_exchange.entities.Skill
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

fun Skill.convertSkillAlone(): SkillDTO {
    val skill = AppFactory.mapToDto(this, SkillDTO::class.java)
    skill.parentId = parentSkillId.id
    return skill
}

interface SkillGettable {
    fun getMainSkills(): List<SkillDTO>
    fun getSkillChlidren(skillId: Int): List<SkillDTO>
    fun getTopCatagorys(): List<SkillDTO>
    fun getAllSkills(): List<SkillDTO>
    fun getAllUnApprovedSkills(): List<SkillDTO>
    fun getServiceUnderSkill(skillId: Int): List<ServiceDTO>
    fun getUserWtihSkill(skillId: Int): List<UserDTO>
}

@Component
private class SkillGettableImpl : SkillGettable {
    @Autowired
    lateinit var skillInterface: SkillInterface

    override fun getMainSkills(): List<SkillDTO> =
            skillInterface.getMainSkills()?.stream()?.map { it.convertSkillAlone() }?.collect(Collectors.toList())
                    ?: emptyList()


    override fun getSkillChlidren(skillId: Int): List<SkillDTO> =
            skillInterface.getSkillChild(skillId)?.stream()?.map { it.convertSkillAlone() }?.collect(Collectors.toList())
                    ?: emptyList()

    override fun getTopCatagorys(): List<SkillDTO> =
            skillInterface.getApprovedSkills()?.stream()?.sorted(compareBy { it.serviceCollection.size })?.map { it.convertSkillAlone() }
                    ?.collect(Collectors.toList()) ?: emptyList()


    override fun getAllSkills(): List<SkillDTO> =
            skillInterface.getApprovedSkills()?.stream()?.map { it.convertSkillAlone() }?.collect(Collectors.toList())
                    ?: emptyList()

    override fun getAllUnApprovedSkills(): List<SkillDTO> =
            skillInterface.getUnAprovedSkills()?.stream()?.map { it.convertSkillAlone() }?.collect(Collectors.toList())
                    ?: emptyList()

    override fun getServiceUnderSkill(skillId: Int): List<ServiceDTO> =
            skillInterface.getSkillById(skillId)?.serviceCollection?.stream()?.map { it.convertServie() }?.collect(Collectors.toList())
                    ?: emptyList()

    override fun getUserWtihSkill(skillId: Int): List<UserDTO> =
            skillInterface.getSkillById(skillId)?.userTableCollection?.stream()?.map { it.convertUser() }?.collect(Collectors.toList())
                    ?: emptyList()

}

interface SkillModifing {
    fun aprroveSkill(skillId: Int): Boolean
    fun updateSkill(skillDTO: SkillDTO): Boolean
}

@Component
class SkillModifingImpl : SkillModifing {
    @Autowired
    lateinit var skillInterface: SkillInterface

    override fun aprroveSkill(skillId: Int): Boolean =
            skillInterface.aprroveSkill(skillId = skillId)


    override fun updateSkill(skillDTO: SkillDTO): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}