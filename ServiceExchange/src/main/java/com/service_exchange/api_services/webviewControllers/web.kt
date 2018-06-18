package com.service_exchange.api_services.webviewControllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@Controller
class Home {
    @RequestMapping(value = ["/"], method = arrayOf(RequestMethod.GET))
    fun getMainView(): String =
            "index"

    @RequestMapping(value = ["/index.html"], method = arrayOf(RequestMethod.GET))
    fun get(): String =
            "index"
}


