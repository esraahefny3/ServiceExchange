package com.service_exchange.api_services.dao.skill

import com.service_exchange.entities.Service
import com.service_exchange.entities.Skill
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
interface SkillData : PagingAndSortingRepository<Skill, Int> {
    fun findByParentSkillId(skill: Skill? = null): List<Skill>
    fun findByIsVerified(isVerified: Int, page: Pageable): Page<Skill>
    fun findByIsVerified(isVerified: Short): List<Skill>
    fun findByUserTableCollectionContains(skills: List<Skill>?): List<UserTable>
    fun findByServiceCollectionContains(skills: List<Skill>?): List<Service>
    fun countAllByIdIsNotNull(): Long?

    fun countAllByParentSkillIdIsNull(): Long?

    fun countAllByParentSkillId_IdEquals(id: Int?): Long?

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
    fun getNumberOfSkills(): Long
    fun getNumberOfParentSkills(): Long
    fun getNumberOfChldren(parentId: Int): Long


}

@Component
private class SkillImpl : SkillInterface {


    val VERIFIED: Short = 1
    val NOT_VERFIED: Short = 2
    @Autowired
    lateinit var skilldata: SkillData

    override fun getApprovedSkills(): List<Skill>? = skilldata.findAll().map { it }.toList()

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

    override fun getNumberOfChldren(parentId: Int): Long =
            skilldata.countAllByParentSkillId_IdEquals(parentId) ?: 0

    override fun getNumberOfParentSkills(): Long =
            skilldata.countAllByParentSkillIdIsNull() ?: 0

    override fun getNumberOfSkills(): Long =
            skilldata.countAllByIdIsNotNull() ?: 0
}
