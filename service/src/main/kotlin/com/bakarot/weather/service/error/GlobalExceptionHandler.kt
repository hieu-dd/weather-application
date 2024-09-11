package com.bakarot.weather.service.error

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {
    companion object {
        private val LOGGER = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleException(request: HttpServletRequest, ex: Exception): ErrorDTO {
        val errorDTO = ErrorDTO()
        errorDTO.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        errorDTO.error = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase
        errorDTO.message = ex.message ?: "An error occurred"
        errorDTO.path = request.servletPath
        LOGGER.error("Error occurred: ${errorDTO.message}", ex)
        return errorDTO
    }
}