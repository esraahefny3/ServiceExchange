package com.service_exchange.api_services.dao.user.userTranaction

import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.entities.TransactionInfo
import org.springframework.beans.factory.annotation.Autowired
import java.util.function.Predicate
import java.util.stream.Collectors

class UserTransactionImpl : UserTransactionInterFace {
    val onSuccess = "sucsesfull";
    val onEnd = "ended"
    @Autowired
    lateinit var userInterface: UserInterFace;

    override fun getSuccsfullTransaction(userId: Int?): MutableList<TransactionInfo> =
            userInterface.getUser(userId)?.transactionInfoCollection?.stream()?.filter(Predicate { t -> t.state == onSuccess })
                    ?.collect(Collectors.toList()) ?: mutableListOf()


    override fun getWorkingDuration(userId: Int?): Long? =
            userInterface.getUser(userId)?.transactionInfoCollection?.stream()
                    ?.mapToLong { t -> t.endDate?.time ?: 0-(t.startDate?.time ?: 0) }?.sum()

    override fun getWoringDurtation(userId: Int?, start: Long, end: Long): Long? =
            userInterface.getUser(userId)?.transactionInfoCollection?.stream()
                    ?.filter { t ->
                        t.startDate?.time ?: 0 >= start && (t.endDate?.time
                                ?: 0) >= end && (t.state == onSuccess || t.state == onEnd)
                    }
                    ?.mapToLong { t -> t.endDate?.time ?: 0-(t.startDate?.time ?: 0) }?.sum()

}