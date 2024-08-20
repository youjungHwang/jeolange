package yjh.jeolange.api.common.response

import yjh.jeolange.api.common.BaseErrorCode
import java.time.LocalDateTime

data class ErrorResponse(
    val status: BaseErrorCode,
    val code: Int,
    val message: String,
    val timestamp: LocalDateTime = LocalDateTime.now(),
)
