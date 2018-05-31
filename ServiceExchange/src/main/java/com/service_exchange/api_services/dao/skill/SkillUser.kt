package com.service_exchange.api_services.dao.skill

import com.service_exchange.entities.Skill
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface SkillUserInterface {

    fun getUsersWithSkills(skills: List<Skill>?): List<UserTable>?
}

@Component
private class SkillUserImpl : SkillUserInterface {
    @Autowired
    lateinit var skillData: SkillData

    override fun getUsersWithSkills(skills: List<Skill>?): List<UserTable>? =
            skillData.findByUserTableCollectionContains(skills)

}