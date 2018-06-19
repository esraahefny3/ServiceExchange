package com.service_exchange.api_services.webviewControllers

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@Configuration
@EnableWebMvc
open class WebConfig : WebMvcConfigurerAdapter() {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry!!.addResourceHandler(
                "**/*.png",
                "**/*.jpg",
                "/*.jpg",
                "/*.css",
                "/*.js",
                "*/*.json")
                .addResourceLocations(

                        "classpath:/static/assets/img/",
                        "classpath:/static/assets/img/",
                        "classpath:/static/",
                        "classpath:/static/css",
                        "classpath:/static/js/",
                        "classpath:/static/assets/")
    }

}