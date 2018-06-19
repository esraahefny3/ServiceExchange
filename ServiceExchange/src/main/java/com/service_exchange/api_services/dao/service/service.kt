package com.service_exchange.api_services.dao.service

import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.entities.Service
import com.service_exchange.entities.Skill
import com.service_exchange.entities.TransactionInfo
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.function.DoubleConsumer
import java.util.stream.Collectors

@Repository
interface ServiceData : PagingAndSortingRepository<Service, Int> {
    @Query("select s from Service s join s.skillCollection t where t in (?1) group by s having count(t) >= (select count(t2) from Skill t2 where t2 in (?1))")
    fun findIfSubsetOfSkillExists(skills: List<Skill>, page: Pageable): List<Service>

    fun findAllByTypeEqualsAndAvailableEquals(type: String, isAvalible: String, pageable: Pageable): Page<Service>

    fun findAllByMadeByEquals(userTable: UserTable): List<Service>

    fun countAllByIdIsNotNull(): Long
    fun countAllByTypeEquals(type: String): Long
    fun countAllByAvailableEquals(isavailbe: String): Long


}

interface ServiceInterface {
    fun createService(service: Service?): Service?
    fun modifieService(service: Service?): Service?
    fun disableService(serviceId: Int): Boolean
    fun getService(serviceId: Int?): Service?
    fun getUserwithIt(serviceId: Int): List<UserTable>
    fun getAllServiceWtihSkill(skills: List<Skill>?, page: Int): List<Service>
    fun getAllServiceMadeByUser(userid: Int?): List<Service>?
    fun getAll(start: Int): Page<Service>
    fun getAll(start: Int, type: String): Page<Service>
    fun countAllservice(): Long
    fun countAllRequset(): Long
    fun countAllOffers(): Long
    fun countAllBaseOnIsAvalibe(isavailbe: String): Long
    fun getMostPubler(size: Int): List<Service>
    fun getTopRated(size: Int): List<Service>
    fun getAll(): List<Service>

}

@Component
private class ServiceImpl : ServiceInterface {


    @Autowired
    lateinit var serviceData: ServiceData

    @Autowired
    lateinit var userInterFace: UserInterFace

    override fun getAll(start: Int): Page<Service> {
        return serviceData.findAll(PageRequest.of(start, 20))
    }

    override fun getAll(): List<Service> {
        return serviceData.findAll().filterIndexed({ index, service -> true })
    }

    override fun getAll(start: Int, type: String): Page<Service> {

        return serviceData.findAllByTypeEqualsAndAvailableEquals(type, Service.AVALIBLE, PageRequest.of(start, 20))
    }

    override fun createService(service: Service?): Service? =
            if (service != null)
                serviceData.save(service)
            else null


    override fun modifieService(service: Service?): Service? =
            if (service != null) {
                serviceData.save(service)

            } else null


    override fun disableService(serviceId: Int): Boolean {
        getService(serviceId)?.takeIf { it.available != Service.DELETED }?.let {
            it.available = Service.PAUSED
            return true
        }
        return false
    }

    override fun getService(serviceId: Int?): Service? {
        serviceId?.let {
            val service = serviceData.findById(serviceId)
            return if (service.isPresent) {
                service.get()
            } else null
        }
        return null
    }

    override fun getUserwithIt(serviceId: Int): List<UserTable> {
        val service = serviceData.findById(serviceId)
        return if (service.isPresent) {
            service.get().transactionInfoCollection?.stream()?.filter { t -> t.state == "complete" }?.map { t -> t.startedBy }?.collect(Collectors.toList())
                    ?.requireNoNulls() ?: emptyList()
        } else emptyList()
    }

    override fun getAllServiceWtihSkill(skills: List<Skill>?, page: Int): List<Service> =
            if (skills != null) {
                serviceData.findIfSubsetOfSkillExists(skills, PageRequest.of(page, 20))
            } else emptyList()


    override fun getAllServiceMadeByUser(userid: Int?): List<Service>? =
            if (userid != null) {
                val user: UserTable? = userInterFace.getUser(userid)
                user?.serviceCollection?.stream()?.collect(Collectors.toList())
            } else null

    override fun countAllOffers(): Long =
            serviceData.countAllByTypeEquals(Service.OFFERED) ?: 0

    override fun countAllRequset(): Long =
            serviceData.countAllByTypeEquals(Service.REQUSETED) ?: 0

    override fun countAllservice(): Long =
            serviceData.countAllByIdIsNotNull()

    override fun getTopRated(size: Int): List<Service> =
            serviceData.findAll().filter { service -> service.type == Service.OFFERED }
                    .sortedByDescending { service ->
                        var avg = 0.0
                        service.transactionInfoCollection?.stream()?.filter { it.state == TransactionInfo.LATE_STATE || it.state == TransactionInfo.COMPLETED_STATE }
                                ?.mapToDouble {
                                    it.reviewCollection
                                            ?.stream()?.mapToDouble { it.rating?.toDouble() ?: 0.0 }
                                            ?.average()?.ifPresent { avg = it }
                                    avg
                                }?.average()?.ifPresent(DoubleConsumer { avg = it })
                        avg
                    }
                    .take(size)

    override fun getMostPubler(size: Int): List<Service> =
            serviceData.findAll().filter { service -> service.type == Service.OFFERED }
                    .sortedBy { service ->
                        service.transactionInfoCollection?.stream()?.filter { it.state == TransactionInfo.LATE_STATE || it.state == TransactionInfo.COMPLETED_STATE }?.count()
                                ?: 0
                    }
                    .take(size)

    override fun countAllBaseOnIsAvalibe(isavailbe: String): Long =
            serviceData.countAllByAvailableEquals(isavailbe)

}