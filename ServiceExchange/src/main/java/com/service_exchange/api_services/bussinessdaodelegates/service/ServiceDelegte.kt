package com.service_exchange.api_services.bussinessdaodelegates.service

import com.service_exchange.api_services.bussinessdaodelegates.user.convertUser
import com.service_exchange.api_services.dao.dto.ServiceDTO
import com.service_exchange.api_services.dao.dto.SkillDTO
import com.service_exchange.api_services.dao.dto.UserDTO
import com.service_exchange.api_services.dao.service.ServiceInterface
import com.service_exchange.api_services.dao.skill.SkillInterface
import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.api_services.factories.AppFactory
import com.service_exchange.entities.Service
import com.service_exchange.entities.Skill
import com.service_exchange.entities.TransactionInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

fun Service.convertServie(): ServiceDTO =
        this.let { t ->
            val ob = AppFactory.mapToDto(t, ServiceDTO::class.java)
            ob.skillList = t.skillCollection.stream().map { it.id }.collect(Collectors.toList())
            ob.uO = com.service_exchange.api_services.dao.dto.UserOwner(t.madeBy.name, t.id)
            ob.numberOfTransaction = t.transactionInfoCollection.stream().filter({ t ->
                t.state == TransactionInfo.COMPLETED_STATE
                        || t.state == TransactionInfo.LATE_STATE
            }).count().toInt()
            ob.rating = t.transactionInfoCollection.stream().filter { t -> t.state == TransactionInfo.COMPLETED_STATE || t.state == TransactionInfo.LATE_STATE }
                    .mapToDouble { t ->
                        t.reviewCollection.stream()
                                .mapToDouble { it.rating.toDouble() }.average().orElse(0.0)
                    }.average().orElse(0.0)
            ob
        }

fun ServiceDTO.convertServie(skillInterface: SkillInterface, userInterface: UserInterFace): Service =
        this.let { t ->
            val ob = AppFactory.mapToDto(t, Service::class.java)
            ob.skillCollection = t.skillList?.stream()?.map { skillInterface.getSkillById(it) }
                    ?.collect(Collectors.toList()) ?: kotlin.collections.emptyList()
            ob.madeBy = userInterface.getUser(t.id)
            ob
        }

fun Skill.convertSkill(): SkillDTO {
    return AppFactory.mapToDto(this, SkillDTO::class.java)
}


interface ServiceGettable {
    fun getAllService(start: Int): List<ServiceDTO>
    fun getAllService(start: Int, type: String): List<ServiceDTO>
    fun getServiceWithSkills(start: Int, skills: List<Int>?): List<ServiceDTO>
    fun getServiceUsers(serviceId: Int): List<UserDTO>
    fun getMostPupler(start: Int): List<ServiceDTO>
    fun getTopRated(start: Int): List<ServiceDTO>
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


    override fun getMostPupler(start: Int): List<ServiceDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTopRated(start: Int): List<ServiceDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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