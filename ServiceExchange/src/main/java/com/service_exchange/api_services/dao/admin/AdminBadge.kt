package com.service_exchange.api_services.dao.admin

import com.service_exchange.api_services.dao.badge.BadgeDataInterface
import com.service_exchange.entities.Badge
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

interface AdminBadgeInterface {
    fun createBadge(badge: Badge?): Badge?
    fun getAllBadgeMadeByAdmin(adminId: Int): List<Badge>?

}

@Component
private class AdminBadgeImpl : AdminBadgeInterface {
    @Autowired
    lateinit var badgeData: BadgeDataInterface
    @Autowired
    lateinit var adminData: AdminInterface

    override fun createBadge(badge: Badge?): Badge? =
            if (badge != null)
                badgeData.save(badge)
            else null


    override fun getAllBadgeMadeByAdmin(adminId: Int): List<Badge>? =
            adminData.getAdmin(adminId)?.badgeCollection?.stream()?.collect(Collectors.toList())


}