package com.service_exchange.api_services.restcontrollers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SecTest {
    @RequestMapping("/hello")
    fun sayHello() =
            "helllo"

}