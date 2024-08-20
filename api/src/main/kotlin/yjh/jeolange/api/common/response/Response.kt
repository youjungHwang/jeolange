package yjh.jeolange.api.common.response

import yjh.jeolange.api.common.BaseErrorCode

data class Response<T>(
    val status: BaseErrorCode = BaseErrorCode.SUCCESS,
    val code: Int = BaseErrorCode.SUCCESS.code,
    val message: String = BaseErrorCode.SUCCESS.message,
    val data: T? = null,
)
