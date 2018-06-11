package com.service_exchange.api_services.bussinesslayer

import com.service_exchange.api_services.dao.dto.ComplaintStatics
import com.service_exchange.api_services.dao.dto.ServiceStatices
import com.service_exchange.api_services.dao.dto.TransactionStatices
import com.service_exchange.api_services.dao.dto.UserStatices

interface AdminBussnessStatic {
    fun getComplaintsStatic(): ComplaintStatics
    fun getServiceStatic(): ServiceStatices
    fun getTransactionStatic(): TransactionStatices
    fun getUserStatic(): UserStatices
}