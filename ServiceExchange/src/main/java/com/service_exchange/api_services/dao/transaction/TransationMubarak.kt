package com.service_exchange.api_services.dao.transaction

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface AdminStaticTransaction {
    fun countAllByIdIsNotNull(): Long

    fun countAllByStateEquals(state: String): Long
}

@Component
private class AdminStaticTransactionImpl : AdminStaticTransaction {
    @Autowired
    lateinit var transacion: TransactionDaoInterface

    override fun countAllByIdIsNotNull(): Long =
            transacion.countAllByIdIsNotNull() ?: 0

    override fun countAllByStateEquals(state: String): Long =
            transacion.countAllByStateEquals(state) ?: 0

}