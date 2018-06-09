package com.service_exchange.api_services.dao.user.userbadge;

import com.service_exchange.entities.UserBadge;
import com.service_exchange.entities.UserBadgePK;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBadeCrudRes extends PagingAndSortingRepository<UserBadge, UserBadgePK> {
}
