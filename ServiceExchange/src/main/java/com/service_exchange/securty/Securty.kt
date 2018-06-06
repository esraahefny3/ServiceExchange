package com.service_exchange.securty

import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.entities.UserTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
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

class CustomUserDetailImpl(val user: UserTable, val authoritys: List<String>) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
            AuthorityUtils.commaSeparatedStringToAuthorityList(authoritys.joinToString(separator = ","));


    override fun isEnabled(): Boolean =
            true

    override fun getUsername(): String =
            user.userEmailCollection.first().userEmailPK.email

    override fun isCredentialsNonExpired(): Boolean =
            true

    override fun getPassword(): String =
            user.accountId

    override fun isAccountNonExpired(): Boolean =
            true

    override fun isAccountNonLocked(): Boolean =
            true

}

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = arrayOf(CustomUserDetail::class))
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class WebSecurtiy : WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var customUserDetail: CustomUserDetail

    @Autowired
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(customUserDetail)
    }

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()?.authorizeRequests()?.antMatchers("/hello")?.hasRole("user")?.anyRequest()?.permitAll()
        http?.authorizeRequests()?.antMatchers("/hello")?.hasRole("user")
                ?.anyRequest()?.permitAll()
        // http?.authorizeRequests()?.antMatchers("/")?.permitAll()
    }


    @Bean("passwordEncoder")
    open fun getPasswordEndcoder(): PasswordEncoder =
            BCryptPasswordEncoder()
}