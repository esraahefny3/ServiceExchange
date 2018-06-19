package com.service_exchange.api_services.bussinesslayer

import com.service_exchange.api_services.KotlinUtal.convertServie
import com.service_exchange.api_services.bussinessdaodelegates.service.ServiceAddAble
import com.service_exchange.api_services.bussinessdaodelegates.service.ServiceGettable
import com.service_exchange.api_services.dao.dto.*
import com.service_exchange.entities.Service
import com.service_exchange.entities.TransactionInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

interface ServiceBussness {
    fun getService(servieId: Int): ServiceDTO?
    fun getAllService(page: Int): List<ServiceDTO>
    fun getAllOfferService(page: Int): List<ServiceDTO>
    fun getAllReqService(page: Int): List<ServiceDTO>
    fun getServiceWithSkills(page: Int, skills: List<Int>): List<ServiceDTO>
    fun getPublerService(size: Int): List<ServiceDTO>
    fun getTopRatedService(size: Int): List<ServiceDTO>
    fun createService(serviceDTO: ServiceDTO?): ServiceDTO?
    fun getAllPreStartTransactionOnService(servieId: Int): List<TransactionEslam>
    fun getAllServices(page: Int): List<ServicesWEB>
    fun getAllRequests(page: Int): List<RequestsWEB>

}


@Component
class ServiceBussnessImpl : ServiceBussness {

    override fun getAllPreStartTransactionOnService(servieId: Int): List<TransactionEslam> {
        return serviceGet.getService(servieId)?.transactionInfoCollection
                ?.stream()?.filter { it.state == TransactionInfo.PENDING_STATE || it.state == TransactionInfo.POSTPONED }
                ?.map {

                    TransactionEslam(UserInfo(userName = it.startedBy?.name, id = it.startedBy?.id, image = it.startedBy?.image)
                            , descrption = it.descrption, numberOfDays = it.duration?.toInt(),
                            date = it.startDate, duration = it.duration?.toInt(), price = it.price, serviceId = it.id)
                }?.collect(Collectors.toList()) ?: emptyList()


    }

    override fun getAllRequests(page: Int): List<RequestsWEB> =
            getAllReqService(page).stream().map {
                RequestsWEB().apply {
                    request_id = it.id
                    request_desc = it.description
                    request_title = it.name
                    numOfPointsPay = it.price
                    user_img = it.uO?.image
                    user_id = it.uO?.id
                }
            }.collect(Collectors.toList())

    override fun getAllServices(page: Int): List<ServicesWEB> =
            getAllReqService(page).stream().map {
                ServicesWEB().apply {
                    service_id = it.id
                    service_img = it.image
                    service_name = it.name
                    numOfReviews = it.revList?.size ?: 0
                    numOfPoints = it.price
                }
            }.collect(Collectors.toList())
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