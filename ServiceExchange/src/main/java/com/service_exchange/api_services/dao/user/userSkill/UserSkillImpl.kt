package com.service_exchange.api_services.dao.user.userSkill

import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.entities.Skill
import org.springframework.beans.factory.annotation.Autowired
import java.util.stream.Collectors

class UserSkillImpl : UserSkillInterFace {
    @Autowired
    lateinit var userInterface: UserInterFace;

    override fun addSkill(skill: Skill?, userId: Int?): Boolean =
            userInterface.getUser(userId)?.addSkill(skill) ?: false

    override fun deleteSkill(skill: Skill?, userId: Int?): Boolean =
            userInterface.getUser(userId)?.removeSkill(skill) ?: false

    override fun getUserSkill(userId: Int?): MutableList<Skill>? =
            userInterface.getUser(userId)?.skillCollection?.stream()?.collect(Collectors.toList())
}