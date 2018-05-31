package com.service_exchange.api_services.dao.admin

import com.service_exchange.entities.AdminTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface AdminInterface {
    fun changePassWord(pass: String, adminId: Int): Boolean
    fun changeImage(image: String, adminId: Int): Boolean
    fun getAdmin(adminId: Int): AdminTable?
    fun save(admin: AdminTable): AdminTable

}

@Component
private class Admin : AdminInterface {

    @Autowired
    lateinit var admindata: AdminDataInterface

    override fun changePassWord(pass: String, adminId: Int): Boolean {
        val admin = getAdmin(adminId)
        return if (admin != null) {
            admin.password = pass
            admindata.save(admin)
            true
        } else false

    }

    override fun changeImage(image: String, adminId: Int): Boolean {
        val admin = getAdmin(adminId)
        return if (admin != null) {
            admin.image = image
            admindata.save(admin)
            true
        } else false
    }


    override fun getAdmin(adminId: Int): AdminTable? =
            admindata.findById(adminId).get()

    override fun save(admin: AdminTable): AdminTable =
            admindata.save(admin)
}