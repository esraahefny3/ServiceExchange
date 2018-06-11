package com.service_exchange.api_services.bussinesslayer

import com.service_exchange.api_services.bussinessdaodelegates.Admin.AdminStatic
import com.service_exchange.api_services.dao.dto.ComplaintStatics
import com.service_exchange.api_services.dao.dto.ServiceStatices
import com.service_exchange.api_services.dao.dto.TransactionStatices
import com.service_exchange.api_services.dao.dto.UserStatices
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface AdminBussnessStatic {
    fun getComplaintsStatic(): ComplaintStatics
    fun getServiceStatic(): ServiceStatices
    fun getTransactionStatic(): TransactionStatices
    fun getUserStatic(): UserStatices

}

@Component
private class AdminBussnessStaticImpl : AdminBussnessStatic {
    @Autowired
    lateinit var adminStatic: AdminStatic

    override fun getComplaintsStatic(): ComplaintStatics =
            ComplaintStatics().let { comp ->
                comp.acceptedCount = adminStatic.getAcceptedComplaint().toInt()
                comp.completedCount = adminStatic.getCompletedComplaint().toInt()
                comp.notReveiwCount = adminStatic.getNotReviwedCompliantCount().toInt()
                comp.onReviewCount = adminStatic.getOnReviewComPlaintCount().toInt()
                comp.rejectedCount = adminStatic.getRejectedComplaint().toInt()
                comp
            }


    override fun getServiceStatic(): ServiceStatices =
            ServiceStatices().apply {
                offeredCount = adminStatic.getNumberOfOffers().toInt()
                reqCount = adminStatic.getNumberOfRequset().toInt()
                pausedCount = adminStatic.getPausedService().toInt()
            }

    override fun getTransactionStatic(): TransactionStatices =
            TransactionStatices().apply {
                activeCount = adminStatic.getNumberOfActiveTransactions().toInt()
                waitingCount = adminStatic.getNumberWaitingTransactions().toInt()
                completedCount = adminStatic.getNumberOfCompletedTransactions().toInt()
                lateCompletedCount = adminStatic.getNumberOfCompletedTransactions().toInt()
                caneledCount = adminStatic.getNumberOfCanceledTransactions().toInt()
                totalPointCount = adminStatic.getTotalPointOnServices().toInt()
            }

    override fun getUserStatic(): UserStatices =
            UserStatices().apply {
                userCount = adminStatic.getNumberOfUser().toInt()
                onlineCount = adminStatic.getUserCountBasedOnStatus(UserTable.ONLINE).toInt()
                busyCount = adminStatic.getUserCountBasedOnStatus(UserTable.BUSY).toInt()
                offlineCount = adminStatic.getUserCountBasedOnStatus(UserTable.OFFLINE).toInt()
            }

}

interface AdminBussnessGettable {

}