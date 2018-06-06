package com.service_exchange.securty.service

import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.securty.CustomUserDetailImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.io.Serializable
import java.util.*


class CustomUserDetail : UserDetailsService {
    @Autowired
    lateinit var userInterFace: UserInterFace

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userInterFace.getUserByEmail(username)
        return if (user == null) {
            throw UsernameNotFoundException("user not found")
        } else {
            CustomUserDetailImpl(user, Arrays.asList("user"))
        }
    }

}

class JwtAuthenticationResponse(val token: String) : Serializable {
    companion object {

        private const val serialVersionUID = 1250166508152483573L
    }
}