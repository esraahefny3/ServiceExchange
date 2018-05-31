package com.service_exchange.api_services.dao.skill

import com.service_exchange.entities.Service
import com.service_exchange.entities.Skill
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface SkillServiceInterface {
    fun getServicsWithSkills(skills: List<Skill>?): List<Service>?
}

@Component
private class SkillServiceImpl : SkillServiceInterface {
    @Autowired
    lateinit var skillData: SkillData

    override fun getServicsWithSkills(skills: List<Skill>?): List<Service>? =
            skillData.findByServiceCollectionContains(skills)
}