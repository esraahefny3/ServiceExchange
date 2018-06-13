package com.service_exchange.api_services.bussinesslayer

import com.service_exchange.api_services.KotlinUtal.convertServie
import com.service_exchange.api_services.bussinessdaodelegates.service.ServiceAddAble
import com.service_exchange.api_services.bussinessdaodelegates.service.ServiceGettable
import com.service_exchange.api_services.dao.dto.ServiceDTO
import com.service_exchange.entities.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface ServiceBussness {
    fun getService(servieId: Int): ServiceDTO?
    fun getAllService(page: Int): List<ServiceDTO>
    fun getAllOfferService(page: Int): List<ServiceDTO>
    fun getAllReqService(page: Int): List<ServiceDTO>
    fun getServiceWithSkills(page: Int, skills: List<Int>): List<ServiceDTO>
    fun getPublerService(size: Int): List<ServiceDTO>
    fun getTopRatedService(size: Int): List<ServiceDTO>
    fun createService(serviceDTO: ServiceDTO?): ServiceDTO?

}

@Component
class ServiceBussnessImpl : ServiceBussness {
    @Autowired
    lateinit var serviceGet: ServiceGettable
    @Autowired
    lateinit var serviceAddAble: ServiceAddAble

    override fun getService(servieId: Int): ServiceDTO? =
            serviceGet.getService(servieId)?.convertServie()

    override fun createService(serviceDTO: ServiceDTO?): ServiceDTO? =
            if (serviceDTO != null)
                serviceAddAble.createService(serviceDTO)
            else null

    override fun getAllService(page: Int): List<ServiceDTO> =
            serviceGet.getAllService(page)

    override fun getAllOfferService(page: Int): List<ServiceDTO> =
            serviceGet.getAllService(start = page, type = Service.OFFERED)


    override fun getAllReqService(page: Int): List<ServiceDTO> =
            serviceGet.getAllService(page, Service.REQUSETED)

    override fun getServiceWithSkills(page: Int, skills: List<Int>): List<ServiceDTO> =
            serviceGet.getServiceWithSkills(page, skills)

    override fun getPublerService(size: Int): List<ServiceDTO> =
            serviceGet.getMostPupler(size)

    override fun getTopRatedService(size: Int): List<ServiceDTO> =
            serviceGet.getTopRated(size)


}