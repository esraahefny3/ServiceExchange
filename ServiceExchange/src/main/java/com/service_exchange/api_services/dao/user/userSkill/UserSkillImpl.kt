package com.service_exchange.api_services.dao.user.userSkill

import com.service_exchange.api_services.dao.skill.SkillInterface
import com.service_exchange.api_services.dao.skill.SkillUserInterface
import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.entities.Skill
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
private class UserSkillImpl : UserSkillInterFace {
    @Autowired
    lateinit var userInterface: UserInterFace;
    @Autowired
    lateinit var skillUser: SkillUserInterface
    @Autowired
    lateinit var skillInterface: SkillInterface

    override fun addSkill(skill: Skill?, userId: Int?): Boolean =
            if (userId != null && skill != null) {
                val user = userInterface.getUser(userId)
                if (skill.id != null) {
                    user?.addSkill(skill)
                } else {
                    val cSkill = skillInterface.createSkill(skill)
                    user?.addSkill(skill)
                }
                if (user != null) {
                    userInterface.updateUser(user)
                    true
                } else false


            } else {
                false
            }

    override fun deleteSkill(skillId: Int, userId: Int): Boolean =
            skillUser.removeSkillFromUser(skillId, userId)

    override fun getUserSkill(userId: Int?): MutableList<Skill>? =
            userInterface.getUser(userId)?.skillCollection?.stream()?.collect(Collectors.toList())
}