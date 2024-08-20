package yjh.jeolange.api.common

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import yjh.jeolange.api.common.response.ErrorResponse

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException): ResponseEntity<ErrorResponse> {
        logger.error { "[ERROR]: ${e.message}]" }
        val errorCode = e.baseErrorCode
        return ResponseEntity.ok(
            ErrorResponse(
                status = errorCode,
                code = errorCode.code,
                message = errorCode.message
            )
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        logger.error { "[ERROR]: ${e.message}}" }
        return ResponseEntity.ok(
            ErrorResponse(
                status = BaseErrorCode.INTERNAL_SERVER_ERROR,
                code = BaseErrorCode.INTERNAL_SERVER_ERROR.code,
                message = BaseErrorCode.INTERNAL_SERVER_ERROR.message
            )
        )
    }
}
