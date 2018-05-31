package com.service_exchange.api_services.dao.skill

import com.service_exchange.entities.Service
import com.service_exchange.entities.Skill
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
interface SkillData : PagingAndSortingRepository<Skill, Int> {
    fun findByParentSkillId(skill: Skill? = null): List<Skill>
    fun findByIsVerified(isVrified: Int): List<Skill>
    fun findByUserTableCollectionContains(skills: List<Skill>?): List<UserTable>
    fun findByServiceCollectionContains(skills: List<Skill>?): List<Service>

}

interface SkillInterface {

    fun createSkill(skill: Skill?): Skill?
    fun getSkillById(skillId: Int): Skill?
    fun getSkillChild(skillId: Int): List<Skill>?
    fun getSkillParent(skillId: Int): Skill?
    fun getMainSkills(): List<Skill>?
    fun getApprovedSkills(): List<Skill>?
    fun updateSkill(skill: Skill?): Skill?
    fun aprroveSkill(skillId: Int): Boolean
    fun getUnAprovedSkills(): List<Skill>?


}

@Component
private class SkillImpl : SkillInterface {


    val VERIFIED: Int = 1
    val NOT_VERFIED: Int = 2
    @Autowired
    lateinit var skilldata: SkillData

    override fun getApprovedSkills(): List<Skill>? = skilldata.findByIsVerified(VERIFIED)

    override fun createSkill(skill: Skill?): Skill? = if (skill != null) skilldata.save(skill) else null

    override fun getSkillById(skillId: Int): Skill? =
            skilldata.findById(skillId).let {
                return@let if (it.isPresent) {
                    it.get()
                } else null
            }


    override fun getSkillChild(skillId: Int): List<Skill>? =
            getSkillById(skillId = skillId)?.skillCollection?.stream()?.collect(Collectors.toList())

    override fun getSkillParent(skillId: Int): Skill? = getSkillById(skillId)?.parentSkillId

    override fun getMainSkills(): List<Skill>? = skilldata.findByParentSkillId()


    override fun updateSkill(skill: Skill?): Skill? = if (skill != null) skilldata.save(skill) else null

    override fun aprroveSkill(skillId: Int): Boolean {
        getSkillById(skillId)?.let { it.isVerified = VERIFIED; return true }
        return false
    }

    override fun getUnAprovedSkills(): List<Skill> = skilldata.findByIsVerified(NOT_VERFIED)

}