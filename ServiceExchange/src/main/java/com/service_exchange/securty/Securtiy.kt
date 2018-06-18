package com.service_exchange.securty

import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.entities.UserTable
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.DefaultClock
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.io.Serializable
import java.security.Principal
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UserSercurity(val id: Int?, private val username: String?, private val password: String?
                    , val email: String?, val authortys: List<GrantedAuthority>?
                    , enabled: Boolean?, val lastPasswordResetDate: Date = Date(Date().time - 1000000)) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
            authortys?.toMutableList() ?: mutableListOf()

    override fun isEnabled(): Boolean {
        return true;
    }

    override fun getUsername(): String =
            username ?: ""

    override fun isCredentialsNonExpired(): Boolean =
            true

    override fun getPassword(): String =
            password ?: ""

    override fun isAccountNonExpired(): Boolean =
            true

    override fun isAccountNonLocked(): Boolean =
            true

    override fun toString(): String {
        return "UserSercurity(id=$id, username='$username', password='$password', email='$email', authortys=$authortys, lastPasswordResetDate=$lastPasswordResetDate)"
    }

}

class JwtFactory {

    companion object {
        fun create(user: UserTable): UserSercurity {
            val email = user.userEmailCollection?.elementAt(0)
            val authority = listOf(user.userAuthority?.authority)
            val u = UserSercurity(id = user.id, email = user.name, authortys = mapToGrantedAuthorities(authority.filterNotNull())
                    , enabled = user.isEnabled, password = user.accountId, username = email?.userEmailPK?.email)

            return u

        }

        private fun mapToGrantedAuthorities(authorities: List<String>): List<GrantedAuthority> {
            return authorities.stream()
                    .map { authority -> SimpleGrantedAuthority(authority) }
                    .collect(Collectors.toList())
        }
    }
}

@Component
class JwtTokenUtil : Serializable {
    @SuppressFBWarnings(value = ["SE_BAD_FIELD"], justification = "It's okay here")
    private val clock = DefaultClock.INSTANCE

    @Value("\${jwt.secret}")
    private val secret: String? = null

    @Value("\${jwt.expiration}")
    private val expiration: Long? = 1000

    fun getUsernameFromToken(token: String): String {
        return getClaimFromToken(token, java.util.function.Function<Claims, String> { it.getSubject() })
    }

    fun getIssuedAtDateFromToken(token: String): Date {
        return getClaimFromToken(token, java.util.function.Function<Claims, Date> { it.getIssuedAt() })
    }

    fun getExpirationDateFromToken(token: String): Date {
        return getClaimFromToken(token, Function<Claims, Date> { it.getExpiration() })
    }

    fun <T> getClaimFromToken(token: String, claimsResolver: java.util.function.Function<Claims, T>): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)

        return expiration.before(clock.now())
    }

    private fun isCreatedBeforeLastPasswordReset(created: Date, lastPasswordReset: Date?): Boolean {

        return lastPasswordReset != null && created.before(lastPasswordReset)
    }

    private fun ignoreTokenExpiration(token: String): Boolean {
        // here you specify tokens, for that the expiration is ignored
        return false
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        return doGenerateToken(claims, userDetails.username)
    }

    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        val createdDate = clock.now()
        val expirationDate = calculateExpirationDate(createdDate)

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }

    fun canTokenBeRefreshed(token: String, lastPasswordReset: Date): Boolean? {
        val created = getIssuedAtDateFromToken(token)
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && (!isTokenExpired(token) || ignoreTokenExpiration(token))
    }

    fun refreshToken(token: String): String {
        val createdDate = clock.now()
        val expirationDate = calculateExpirationDate(createdDate)

        val claims = getAllClaimsFromToken(token)
        claims.issuedAt = createdDate
        claims.expiration = expirationDate

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean? {
        val user = userDetails as UserSercurity
        val username = getUsernameFromToken(token)
        val created = getIssuedAtDateFromToken(token)
        //final Date expiration = getExpirationDateFromToken(token);
        println(username == user.getUsername())
        return (username == user.getUsername()
                && !isTokenExpired(token)
                && !isCreatedBeforeLastPasswordReset(created, user.lastPasswordResetDate))
    }

    private fun calculateExpirationDate(createdDate: Date): Date {
        return Date(createdDate.time + expiration!! * 1000)
    }

    companion object {

        internal val CLAIM_KEY_USERNAME = "sub"
        internal val CLAIM_KEY_CREATED = "iat"
        private const val serialVersionUID = -3301605591108950415L
    }
}

//here i can change the header value and the header prefix
class JwtAuthorizationTokenFilter(private val userDetailsService: UserDetailsService, private val jwtTokenUtil: JwtTokenUtil, private val tokenHeader: String) : OncePerRequestFilter() {

    private val logger2: Logger = LoggerFactory.getLogger(this.javaClass)

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        logger2.debug("processing authentication for '{}'", request.requestURL)

        val requestHeader = request.getHeader(this.tokenHeader)

        var username: String? = null
        var authToken: String? = null
        println(requestHeader)
        if (requestHeader != null && requestHeader.startsWith(":")) {
            authToken = requestHeader.substring(1)
            println(authToken + " filter")
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken)
                println(username + " filter")
            } catch (e: IllegalArgumentException) {
                logger2.error("an error occured during getting username from token", e)
            } catch (e: ExpiredJwtException) {
                logger2.warn("the token is expired and not valid anymore", e)
            }

        } else {
            logger2.warn("couldn't find : string, will ignore the header")
        }

        logger2.debug("checking authentication for user '{}'", username)
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            logger2.debug("security context was null, so authorizating user")

            // It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)
            val userDetails = this.userDetailsService.loadUserByUsername(username)
            println(userDetails)
            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
            // the database compellingly. Again it's up to you ;)
            if (jwtTokenUtil.validateToken(authToken ?: "", userDetails) ?: false) {
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                logger2.info("authorizated user '{}', setting security context", username)
                SecurityContextHolder.getContext().authentication = authentication
                println(authentication)
            }
        }

        chain.doFilter(request, response)
    }
}

//holdes the username and the password
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

    override fun toString(): String {
        return "JwtAuthenticationRequest(username=$username, password=$password)"
    }

}

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint, Serializable {
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: org.springframework.security.core.AuthenticationException?) {
        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
    }


    companion object {

        private const val serialVersionUID = -8970718410437077606L
    }
}

//service
@Service("userDetailService")
class UserDetailService : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserInterFace
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    override fun loadUserByUsername(username: String?): UserDetails {
        val user: UserTable? = userRepository.getUserByEmail(username)
        if (user != null) {
            user.accountId = passwordEncoder.encode(user.accountId)
            return JwtFactory.create(user)

        } else throw UsernameNotFoundException(String.format("No user found with username '%s'.", username)) as Throwable
    }


}

class JwtAuthenticationResponse(val token: String) : Serializable {
    companion object {

        private const val serialVersionUID = 1250166508152483573L
    }
}

//service

//restControllers
class AuthenticationException(message: String, cause: Throwable) : RuntimeException(message, cause)
@RestController
class AuthenticationRestController {

    @Value("\${jwt.header}")
    private val tokenHeader: String? = null

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    @Autowired
    @Qualifier("userDetailService")
    private val userDetailsService: UserDetailsService? = null

    @RequestMapping(value = ["\${jwt.route.authentication.path}"], method = arrayOf(RequestMethod.POST))
    @Throws(AuthenticationException::class)
    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtAuthenticationRequest): ResponseEntity<*> {
        print(authenticationRequest)
        authenticate(authenticationRequest.username ?: "", authenticationRequest.password ?: "")

        // Reload password post-security so we can generate the token
        val userDetails = userDetailsService!!.loadUserByUsername(authenticationRequest.username)
        val token = jwtTokenUtil!!.generateToken(userDetails)

        // Return the token
        return ResponseEntity.ok<Any>(JwtAuthenticationResponse(token))
    }

    @RequestMapping(value = ["\${jwt.route.authentication.refresh}"], method = arrayOf(RequestMethod.GET))
    fun refreshAndGetAuthenticationToken(request: HttpServletRequest): ResponseEntity<*> {
        val authToken = request.getHeader(tokenHeader)
        val token = authToken.substring(1)
        val username = jwtTokenUtil!!.getUsernameFromToken(token)
        val user = userDetailsService!!.loadUserByUsername(username) as UserSercurity

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.lastPasswordResetDate) ?: false) {
            val refreshedToken = jwtTokenUtil.refreshToken(token)
            return ResponseEntity.ok<Any>(JwtAuthenticationResponse(refreshedToken))
        } else {
            return ResponseEntity.badRequest().body<Any>(null)
        }
    }

    @RequestMapping("/face")
    fun user(principal: Principal?): Principal? {
        return principal
    }


    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(e: AuthenticationException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
    }

    /**
     * Authenticates the user. If something is wrong, an [AuthenticationException] will be thrown
     */
    private fun authenticate(username: String, password: String) {
        Objects.requireNonNull(username)
        Objects.requireNonNull(password)

        try {
            authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw AuthenticationException("User is disabled!", e)
        } catch (e: BadCredentialsException) {
            throw AuthenticationException("Bad credentials!", e)
        }

    }
}
//restControllers

//config
@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val unauthorizedHandler: JwtAuthenticationEntryPoint? = null

    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    @Autowired
    @Qualifier("userDetailService")
    lateinit var jwtUserDetailsService: UserDetailsService

    @Value("\${jwt.header}")
    lateinit var tokenHeader: String


    @Value("\${jwt.route.authentication.path}")
    private val authenticationPath: String? = null

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .userDetailsService<UserDetailsService>(jwtUserDetailsService)
                .passwordEncoder(passwordEncoderBean())

    }

    @Bean
    open fun passwordEncoderBean(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()


                .antMatchers("/**", "/auth", "/user/logInOrSignup").permitAll()

        //.antMatchers("/**").authenticated()


        println(tokenHeader)
        val authenticationTokenFilter = JwtAuthorizationTokenFilter(userDetailsService(), jwtTokenUtil, tokenHeader)
        httpSecurity
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter::class.java)

        // disable page caching
        httpSecurity
                .headers()
                .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
                .cacheControl()
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity?) {
        // AuthenticationTokenFilter will ignore the below paths
        web!!
                .ignoring()
                .antMatchers(
                        HttpMethod.POST,
                        authenticationPath!!

                )

                // allow anonymous resource requests
                .and()
                .ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"

                )

                // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
                .and()
                .ignoring()
                .antMatchers("/h2-console/**/**")
    }


}


///////////////social






