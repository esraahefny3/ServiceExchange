package com.service_exchange.api_services.restcontrollers

import com.service_exchange.api_services.bussinesslayer.AdminBussnessCrud
import com.service_exchange.api_services.bussinesslayer.AdminBussnessGettable
import com.service_exchange.api_services.bussinesslayer.AdminBussnessStatic
import com.service_exchange.api_services.dao.dto.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/admin"])
class AdminRest {
    @Autowired
    private
    lateinit var statistec: AdminBussnessStatic
    @Autowired
    lateinit var adminGet: AdminBussnessGettable
    @Autowired
    lateinit var adminCrud: AdminBussnessCrud

    @RequestMapping(value = ["/getUsersStatistics"], method = [(RequestMethod.GET)])
    fun getUsersStatistics(): UserStatices =
            statistec.getUserStatic()

    @RequestMapping(value = ["/getServicesStatistics"], method = [(RequestMethod.GET)])
    fun getServicesStatistics(): ServiceStatices =
            statistec.getServiceStatic()

    @RequestMapping(value = ["/getSkillStatistics"], method = [(RequestMethod.GET)])
    fun getSkillStatistics(): SkillStatistics =
            statistec.getSkillStatices()

    @RequestMapping(value = ["/getTransactionStatistics"], method = [(RequestMethod.GET)])
    fun getTransactionStatistics(): TransactionStatices =
            statistec.getTransactionStatic()

    @RequestMapping(value = ["/getAllnotifecation"], method = [(RequestMethod.GET)])
    fun getAllNotifecation(@RequestParam page: Int): List<NotificationDto> =
            adminGet.getAllNotifecation(page)

    @RequestMapping(value = ["/getServicesDetails"], method = [(RequestMethod.GET)])
    fun getServiceDetails(@RequestParam page: Int, @RequestParam type: String): List<ServiceAdminInfo> =
            adminGet.getAllServiceWithSimpleDetails(page, type)

    @RequestMapping(value = ["/getUserssDetails"], method = [(RequestMethod.GET)])
    fun getServiceDetails(@RequestParam page: Int): List<UserAdminInfo> =
            adminGet.getAllUsersWithSimpleDetails(page)

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////Admin Cruds////////////////////////////////////////////////////
    @RequestMapping(value = ["/createAdmin"], method = [(RequestMethod.POST)])
    fun createAdmin(@RequestBody admin: AdminMain): AdminMain? =
            adminCrud.createAdmin(admin)

    @RequestMapping(value = ["/disableAdmin"], method = [(RequestMethod.POST)])
    fun disableAdmin(@RequestBody adminId: String): Boolean =
            adminCrud.disableAdmin(adminId)

    @RequestMapping(value = ["/enableAdmin"], method = [(RequestMethod.POST)])
    fun enableAdmin(@RequestBody adminId: String): Boolean =
            adminCrud.enableAdmin(adminId)

    @RequestMapping(value = ["/getAllAdmin"], method = [(RequestMethod.GET)])
    fun getAllAdmin(page: Int = 0): List<AdminMain> =
            adminGet.getAllAdmins(page = page)

    //////////////////////////////////skill//////////////////////////////////////////////////////////
    @RequestMapping(value = ["/createSkill"], method = [(RequestMethod.POST)])
    fun createSkill(@RequestBody skill: SkillDTO): SkillDTO? =
            adminCrud.addSkill(skill)

    @RequestMapping(value = ["/aproveSkill"], method = [(RequestMethod.POST)])
    private fun aproveSkill(@RequestBody skillId: Int): Boolean =
            adminCrud.approveSkill(skillId)

    @RequestMapping(value = ["/disableSkill"], method = [(RequestMethod.POST)])
    fun disableSkill(@RequestBody skillId: Int): Boolean =
            adminCrud.disableSkill(skillId)

    @RequestMapping(value = ["/deleteSkill"], method = [(RequestMethod.POST)])
    fun deleteSkill(@RequestBody skillId: Int): Boolean =
            adminCrud.deleteSkill(skillId)

    @RequestMapping(value = ["/restoreSkill"], method = [(RequestMethod.POST)])
    fun restoreSkill(@RequestBody skillId: Int): Boolean =
            adminCrud.restoreSkill(skillId)

    ///////////////////////////////user/////////////////////////////////////////////////////////
    @RequestMapping(value = ["/enableUser"], method = arrayOf(RequestMethod.POST))
    private fun enableUser(@RequestBody userId: Int): Boolean =
            adminCrud.enableUser(userId)

    @RequestMapping(value = ["/disableUser"], method = [(RequestMethod.POST)])
    fun disableUser(@RequestBody userId: Int): Boolean =
            adminCrud.disableUser(userId)

    ///////////////////////////////service///////////////////////////////////////////////////////
    @RequestMapping(value = ["/enableService"], method = [(RequestMethod.POST)])
    fun enableService(@RequestBody serviceId: Int): Boolean =
            adminCrud.enableService(serviceId)

    @RequestMapping(value = ["/disableService"], method = [(RequestMethod.POST)])
    fun disableService(@RequestBody serviceId: Int): Boolean =
            adminCrud.disableService(serviceId)
    ////////////////////////////complaint/////////////////////////////////////////////////////////

    @RequestMapping(value = ["/acceptComplain"], method = [(RequestMethod.POST)])
    fun acceptComplain(@RequestBody complaintId: Int, adminId: String): Boolean =
            adminCrud.acceptComplaint(complaintId, adminId)

    @RequestMapping(value = ["/rejectComplaint"], method = [(RequestMethod.POST)])
    fun rejectComplaint(@RequestBody complaintId: Int): Boolean =
            adminCrud.rejectComplaint(complaintId)

    @RequestMapping(value = ["/getComplaintChat"], method = [(RequestMethod.GET)])
    fun getComplaintChat(@RequestBody complaintId: Int): List<MessageComplaintDto> =
            adminGet.getAllComlaintChat(complaintId)

    @RequestMapping(value = ["/getComplaintTransactionChat"], method = [(RequestMethod.GET)])
    fun getComplaintTransactionChat(@RequestBody complaintId: Int): List<MessageTransactionDto> =
            adminGet.getAllComlaintsTransactionChat(complaintId)

    @RequestMapping(value = ["/getAllAdminOpenedComplaint"], method = [(RequestMethod.GET)])
    fun getAllAdminOpenedComplaint(page: Int, adminId: String): List<AdminComplaint> =
            adminGet.getAdminOpenComplains(page = page, adminId = adminId)

    @RequestMapping(value = ["/getAllOpenedComplaint"], method = [(RequestMethod.GET)])
    fun getAllOpenedComplaint(page: Int): List<AdminComplaint> =
            adminGet.getAllUnReviewedComplaint(page = page)


}