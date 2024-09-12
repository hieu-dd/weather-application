package com.bakarot.weather.service.util

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory


object CommonUtility {
    private val LOGGER: Logger = LoggerFactory.getLogger(CommonUtility::class.java)

    fun getIPAddress(request: HttpServletRequest): String {
        var ip = request.getHeader("X-FORWARDED-FOR")
        if (ip == null || ip.isEmpty()) {
            ip = request.remoteAddr
        }
        LOGGER.info("Client's IP Address: $ip")
        return ip
    }
}