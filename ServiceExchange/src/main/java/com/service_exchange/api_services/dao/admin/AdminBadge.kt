package com.service_exchange.api_services.dao.admin

import com.service_exchange.api_services.dao.badge.BadgeDataInterface
import com.service_exchange.entities.Badge
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

interface AdminBadgeInterface {
    fun createBadge(badge: Badge?): Badge?
    fun getAllBadgeMadeByAdmin(adminId: String): List<Badge>?
    fun disableBadge(badgeId: Int): Boolean
    fun enableBadge(badgeId: Int): Boolean

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


    override fun getAllBadgeMadeByAdmin(adminId: String): List<Badge>? =
            adminData.getAdmin(adminId)?.badgeCollection?.stream()?.collect(Collectors.toList())

    override fun disableBadge(badgeId: Int): Boolean {
        var bool = false
        badgeData.findById(badgeId).ifPresent { t ->
            t.isDeleted = Badge.Deleted
            bool = true
        }
        return bool
    }

    override fun enableBadge(badgeId: Int): Boolean {
        var bool = false
        badgeData.findById(badgeId).ifPresent { t ->
            t.isDeleted = Badge.ENABLED
            bool = true
        }
        return bool
    }
}