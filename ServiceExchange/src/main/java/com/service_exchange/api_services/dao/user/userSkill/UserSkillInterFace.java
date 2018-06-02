/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.user.userSkill;

import com.service_exchange.entities.Skill;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 *
 * @author Altysh
 */
public interface UserSkillInterFace {
     Boolean addSkill(Skill skill, Integer userId);

    Boolean deleteSkill(Integer skillId, Integer userId);

    @Nullable
    List<Skill> getUserSkill(Integer userId);
}
