package com.service_exchange.api_services.webviewControllers

import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter





@Controller
class Home {
    @RequestMapping(value = ["/"], method = arrayOf(RequestMethod.GET))
    fun getMainView(): String =
            "index"
}


@Configuration
@EnableWebMvc
open class WebAppConfig : WebMvcConfigurerAdapter() {

    override fun addViewControllers(registry: ViewControllerRegistry?) {
        registry!!.addRedirectViewController("/", "index")

    }

    override fun configureViewResolvers(registry: ViewResolverRegistry?) {
        registry!!.jsp("resources\\WEB-INF\\webViews\\project", ".html")
    }

}

