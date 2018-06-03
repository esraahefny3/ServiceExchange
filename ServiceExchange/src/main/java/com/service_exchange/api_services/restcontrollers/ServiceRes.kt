package com.service_exchange.api_services.restcontrollers

import com.service_exchange.api_services.bussinesslayer.ServiceBussness
import com.service_exchange.api_services.dao.dto.ServiceDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/service")
class ServiceRestfull {
    @Autowired
    lateinit var serviceBussness: ServiceBussness

    @RequestMapping(value = ["/getOffered"], method = arrayOf(RequestMethod.GET))
    fun getAllOfferedService(page: Int): List<ServiceDTO> =
            serviceBussness.getAllOfferService(page)

    @RequestMapping(value = ["/getReq"], method = arrayOf(RequestMethod.GET))
    fun getAllReqService(page: Int): List<ServiceDTO> =
            serviceBussness.getAllReqService(page)

    @RequestMapping(value = ["/getAll"], method = arrayOf(RequestMethod.GET))
    fun getAllService(page: Int): List<ServiceDTO> =
            serviceBussness.getAllService(page)

    @RequestMapping(value = ["/getAllWithSkills"], method = arrayOf(RequestMethod.GET))
    fun getServiceWithSkills(page: Int, skills: List<Int>): List<ServiceDTO> =
            serviceBussness.getServiceWithSkills(page, skills)


}