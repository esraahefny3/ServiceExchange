package com.service_exchange.api_services.bussinesslayer

import com.service_exchange.api_services.bussinessdaodelegates.skill.SkillGettable
import com.service_exchange.api_services.bussinessdaodelegates.skill.SkillModifing
import com.service_exchange.api_services.dao.dto.ServiceDTO
import com.service_exchange.api_services.dao.dto.SkillDTO
import com.service_exchange.api_services.dao.dto.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface SkillBussness {
    fun getAllSkills(): List<SkillDTO>
    fun getMainSkill(): List<SkillDTO>
    fun getSkillChildern(skillId: Int?): List<SkillDTO>
    fun getServiceUsedBySkill(skillId: Int?): List<ServiceDTO>
    fun getUsersWithSkill(skillId: Int?): List<UserDTO>
    fun approveSkill(skillId: Int?): Boolean
    fun updateSkill(skillId: SkillDTO?): Boolean
}

@Component
private class SkillBussnessImpl : SkillBussness {
    @Autowired
    lateinit var skillGettable: SkillGettable
    @Autowired
    lateinit var skillModifing: SkillModifing

    override fun getAllSkills(): List<SkillDTO> =
            skillGettable.getAllSkills()

    override fun getMainSkill(): List<SkillDTO> =
            skillGettable.getMainSkills()

    override fun getSkillChildern(skillId: Int?): List<SkillDTO> =
            skillId?.let { skillGettable.getSkillChlidren(it) } ?: emptyList()

    override fun getServiceUsedBySkill(skillId: Int?): List<ServiceDTO> =
            skillId?.let { skillGettable.getServiceUnderSkill(skillId) ?: emptyList() } ?: emptyList()

    override fun getUsersWithSkill(skillId: Int?): List<UserDTO> =
            skillId?.let { skillGettable.getUserWtihSkill(skillId) ?: emptyList() } ?: emptyList()

    override fun approveSkill(skillId: Int?): Boolean = skillId?.let { skillModifing.aprroveSkill(skillId = skillId) }
            ?: false

    override fun updateSkill(skillId: SkillDTO?): Boolean = skillId?.let { skillModifing.updateSkill(skillId) } ?: false

}