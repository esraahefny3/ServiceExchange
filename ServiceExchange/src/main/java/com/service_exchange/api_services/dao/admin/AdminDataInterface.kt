package com.service_exchange.api_services.dao.admin

import com.service_exchange.entities.AdminTable
import org.springframework.data.repository.PagingAndSortingRepository

interface AdminDataInterface : PagingAndSortingRepository<AdminTable, Int>{
     fun findAdminTableByEmail(adminEmail: String): AdminTable
}
