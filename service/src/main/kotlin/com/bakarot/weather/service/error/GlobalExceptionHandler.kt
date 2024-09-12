package com.bakarot.weather.service.error

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    companion object {
        private val LOGGER = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleException(request: HttpServletRequest, ex: Exception): ErrorDTO {
        val errorDTO = ErrorDTO()
        errorDTO.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        errorDTO.errors.add(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
        errorDTO.path = request.servletPath
        LOGGER.error("Error occurred: ${ex.message}", ex)
        return errorDTO
    }

    // valid path parameter/ query parameter
    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleConstraintViolationException(request: HttpServletRequest, ex: ConstraintViolationException): ErrorDTO {
        val error = ErrorDTO()

        error.status = HttpStatus.BAD_REQUEST.value()
        error.path = request.servletPath
        error.errors.add(ex.message.orEmpty())
        LOGGER.error(ex.message, ex)

        return error
    }

    // valid request body
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errorDTO = ErrorDTO()
        errorDTO.status = HttpStatus.BAD_REQUEST.value()
        ex.bindingResult.fieldErrors.forEach {
            errorDTO.errors.add(it.defaultMessage!!)
        }
        errorDTO.path = (request as? ServletWebRequest)?.request?.servletPath.orEmpty()
        return ResponseEntity.badRequest().body(errorDTO)
    }
}