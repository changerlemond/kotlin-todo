package server.project.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import server.project.dto.exception.ExceptionDto

@ControllerAdvice
class RestExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(RestExceptionHandler::class.java.name)

    @ExceptionHandler(BadCredentialsException::class)
    @ResponseStatus(UNAUTHORIZED)
    fun handleBadCredentialsException(exception: Exception): ResponseEntity<ExceptionDto> {
        logger.warn(exception.message, exception)
        return ResponseEntity
            .badRequest()
            .body(ExceptionDto(ExceptionCode.AUTH, exception.message))
    }

    @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class)
    fun handleParameterExceptions(exception: Exception): ResponseEntity<ExceptionDto> {
        logger.warn(exception.message, exception)
        return ResponseEntity
            .badRequest()
            .body(ExceptionDto(ExceptionCode.PARAM, exception.message))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ExceptionDto> {
        logger.error(exception.message, exception)
        return ResponseEntity
            .internalServerError()
            .body(ExceptionDto(ExceptionCode.UNKNOWN, exception.message))
    }

}