package yjh.jeolange.api.board.domain

import yjh.jeolange.api.common.BaseErrorCode
import yjh.jeolange.api.common.BaseException

class BoardValidator {
    companion object {
        const val TITLE_MIN_LENGTH = 1
        const val TITLE_MAX_LENGTH = 20

        const val CONTENT_MAX_LENGTH = 200

        fun validate(value: Boolean, lazyErrorCode: () -> BaseErrorCode) {
            if (!value) throw BaseException(lazyErrorCode())
        }
    }
}
