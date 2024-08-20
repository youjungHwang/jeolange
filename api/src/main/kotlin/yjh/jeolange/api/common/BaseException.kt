package yjh.jeolange.api.common

class BaseException(
    val baseErrorCode: BaseErrorCode,
) : RuntimeException(baseErrorCode.message)
