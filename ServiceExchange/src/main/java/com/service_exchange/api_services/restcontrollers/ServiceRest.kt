package com.service_exchange.api_services.restcontrollers

import com.service_exchange.api_services.KotlinUtal.convertToServcieWEB
import com.service_exchange.api_services.bussinesslayer.ServiceBussness
import com.service_exchange.api_services.dao.dto.RequestsWEB
import com.service_exchange.api_services.dao.dto.ServiceDTO
import com.service_exchange.api_services.dao.dto.ServicesWEB
import com.service_exchange.api_services.dao.dto.TransactionEslam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@CrossOrigin(origins = arrayOf("*"))
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

    @RequestMapping(value = ["/getTopRated"], method = arrayOf(RequestMethod.GET))
    fun getTopRated(@RequestParam size: Int): List<ServiceDTO> =
            serviceBussness.getTopRatedService(size)

    @RequestMapping(value = ["/getPub"], method = arrayOf(RequestMethod.GET))
    fun getPub(@RequestParam size: Int): List<ServiceDTO> =
            serviceBussness.getPublerService(size)

    @RequestMapping(value = ["/getAllRequstOnService"], method = arrayOf(RequestMethod.GET))
    fun getAllRequstOnService(serviceId: Int): List<TransactionEslam> =
            serviceBussness.getAllPreStartTransactionOnService(serviceId)

    //hoda
    @RequestMapping(value = ["/getAllService"], method = arrayOf(RequestMethod.GET))
    fun getAllServices(page: Int): List<ServicesWEB> =
            serviceBussness.getAllServices(page)

    @RequestMapping(value = ["/getAllRequests"], method = arrayOf(RequestMethod.GET))
    fun getAllRequests(page: Int): List<RequestsWEB> =
            serviceBussness.getAllRequests(page)

    @RequestMapping(value = ["/getTopService"], method = arrayOf(RequestMethod.GET))
    fun getTopServices(size: Int): List<ServicesWEB> =
            serviceBussness.getTopRatedService(size).stream().map {
                it.convertToServcieWEB()
            }.collect(Collectors.toList())



}