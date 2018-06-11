package com.service_exchange.api_services.dao.admin

import com.service_exchange.entities.AdminTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

interface AdminInterface {
    fun changePassWord(pass: String, adminId: String): Boolean
    fun changeImage(image: String, adminId: String): Boolean
    fun getAdmin(adminId: String): AdminTable?
    fun save(admin: AdminTable): AdminTable
    fun getAllAdmins(page: Int): List<AdminTable>

}

@Component
private class Admin : AdminInterface {

    @Autowired
    lateinit var admindata: AdminDataInterface

    override fun changePassWord(pass: String, adminId: String): Boolean {
        val admin = getAdmin(adminId)
        return if (admin != null) {
            admin.password = pass
            admindata.save(admin)
            true
        } else false

    }

    override fun getAllAdmins(page: Int): List<AdminTable> =
            admindata.findAll(PageRequest.of(page, 20)).map { it }.content

    override fun changeImage(image: String, adminId: String): Boolean {
        val admin = getAdmin(adminId)
        return if (admin != null) {
            admin.image = image
            admindata.save(admin)
            true
        } else false
    }


    override fun getAdmin(adminId: String): AdminTable? =
            admindata.findById(adminId).get()

    override fun save(admin: AdminTable): AdminTable =
            admindata.save(admin)
}