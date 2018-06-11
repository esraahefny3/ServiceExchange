package com.service_exchange.api_services.bussinessdaodelegates.service

import com.service_exchange.api_services.KotlinUtal.convertServie
import com.service_exchange.api_services.bussinessdaodelegates.user.convertUser
import com.service_exchange.api_services.dao.dto.ServiceDTO
import com.service_exchange.api_services.dao.dto.UserDTO
import com.service_exchange.api_services.dao.service.ServiceInterface
import com.service_exchange.api_services.dao.skill.SkillInterface
import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.entities.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors



interface ServiceGettable {
    fun getAllService(start: Int): List<ServiceDTO>
    fun getAllService(start: Int, type: String): List<ServiceDTO>
    fun getServiceWithSkills(start: Int, skills: List<Int>?): List<ServiceDTO>
    fun getServiceUsers(serviceId: Int): List<UserDTO>
    fun getMostPupler(size: Int): List<ServiceDTO>
    fun getTopRated(size: Int): List<ServiceDTO>
    fun getService(serviceId: Int): Service?
}

interface ServiceAddAble {
    fun createService(service: ServiceDTO): ServiceDTO?
}

@Component
class ServiceGettableImpl : ServiceGettable {
    @Autowired
    lateinit var serviceInterface: ServiceInterface
    @Autowired
    lateinit var skillInterface: SkillInterface

    override fun getService(serviceId: Int): Service? {
        return serviceInterface.getService(serviceId)
    }

    override fun getAllService(start: Int): List<ServiceDTO> =
            serviceInterface.getAll(start).stream().map { t -> t.convertServie() }.collect(Collectors.toList())

    override fun getAllService(start: Int, type: String): List<ServiceDTO> =
            serviceInterface.getAll(start, type).stream().map { it.convertServie() }.collect(Collectors.toList())


    override fun getServiceWithSkills(start: Int, skills: List<Int>?): List<ServiceDTO> {
        return if (skills != null) {
            val lsit = skills.stream()
                    .map { skillInterface.getSkillById(it) }.filter { it != null }
                    .collect(Collectors.toList()).filterNotNull()
            serviceInterface.getAllServiceWtihSkill(skills = lsit, page = start).stream()
                    .map { it.convertServie() }.collect(Collectors.toList())

        } else emptyList<ServiceDTO>()

    }

    override fun getServiceUsers(serviceId: Int): List<UserDTO> =
            serviceInterface.getUserwithIt(serviceId).stream().map { it.convertUser() }.collect(Collectors.toList())


    override fun getMostPupler(size: Int): List<ServiceDTO> =
            serviceInterface.getMostPubler(size).stream().map { it.convertServie() }.collect(Collectors.toList())

    override fun getTopRated(size: Int): List<ServiceDTO> =
            serviceInterface.getTopRated(size).stream().map { it.convertServie() }.collect(Collectors.toList())

}

@Component
class ServiceAddAbleImpl : ServiceAddAble {
    @Autowired
    lateinit var serviceInterface: ServiceInterface
    @Autowired
    lateinit var userInterFace: UserInterFace
    @Autowired
    lateinit var skillInterface: SkillInterface

    override fun createService(service: ServiceDTO): ServiceDTO? =
            serviceInterface.createService(service = service.convertServie(skillInterface, userInterFace))?.convertServie()

}