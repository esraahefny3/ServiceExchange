package com.service_exchange.api_services.dao.admin

import com.service_exchange.api_services.dao.transaction.TransactionDaoInterface
import org.springframework.beans.factory.annotation.Autowired

interface AdminTransactionInterface {
    //true for owner to client
    //false for client to owner
    fun discountAmountFromUser(transactionId: Int, way: Boolean, amount: Int)
}

private class AdminTransactionImpl : AdminTransactionInterface {
    @Autowired
    lateinit var transactionInterface: TransactionDaoInterface

    override fun discountAmountFromUser(transactionId: Int, way: Boolean, amount: Int) {

    }

}