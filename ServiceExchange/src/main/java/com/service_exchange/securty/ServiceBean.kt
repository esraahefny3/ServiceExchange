package com.service_exchange.securty

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import java.io.IOException
import java.io.Serializable
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationRequest : Serializable {

    var username: String? = null
    var password: String? = null

    constructor() : super() {}

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }

    companion object {

        private const val serialVersionUID = -8445943548965154778L
    }
}

class JwtAuthenticationEntryPoint : AuthenticationEntryPoint, Serializable {

    @Throws(IOException::class)
    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          authException: AuthenticationException) {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
    }

    companion object {

        private const val serialVersionUID = -8970718410437077606L
    }
}

