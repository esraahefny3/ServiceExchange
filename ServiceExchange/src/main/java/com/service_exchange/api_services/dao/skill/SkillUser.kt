package com.service_exchange.api_services.dao.skill

import com.service_exchange.entities.Skill
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface SkillUserInterface {

    fun getUsersWithSkills(skills: List<Skill>?): List<UserTable>?
    fun removeSkill(skillId: Int?): Boolean
    fun removeSkillFromUser(skillId: Int?, userId: Int?): Boolean
    fun getSkillById(skillId: Int?): Skill?

}

@Component
private class SkillUserImpl : SkillUserInterface {


    @Autowired
    lateinit var skillData: SkillData

    override fun getUsersWithSkills(skills: List<Skill>?): List<UserTable>? =
            skillData.findByUserTableCollectionContains(skills)

    override fun removeSkill(skillId: Int?): Boolean =
            if (skillId != null) {
                val skill = skillData.findById(skillId)
                if (skill.isPresent) {
                    skillData.delete(skill.get())
                    true
                } else false
            } else false

    override fun removeSkillFromUser(skillId: Int?, userId: Int?): Boolean {
        var boolean = false
        getSkillById(skillId)?.apply {
            userTableCollection?.stream()?.filter { it.id == userId }?.findFirst()?.ifPresent { userTableCollection?.remove(it); boolean = true; };
            skillData.save(this)
        }
        return boolean
    }

    override fun getSkillById(skillId: Int?): Skill? =
            if (skillId != null) {
                val skill = skillData.findById(skillId)
                if (skill.isPresent) {
                    skill.get()
                } else null

            } else null

}