package yjh.jeolange.api.board.domain

import yjh.jeolange.api.board.domain.BoardValidator.Companion.validate
import yjh.jeolange.api.common.BaseErrorCode

class BoardUpdateCommand(
    val title: String,
    val content: String,
) {
    init {
        validate(title.isNotBlank()) { BaseErrorCode.NOT_EMPTY_TITLE }
        validate(content.isNotBlank()) { BaseErrorCode.NOT_EMPTY_CONTENT }
        validate(title.length > BoardValidator.TITLE_MIN_LENGTH) { BaseErrorCode.TITLE_TOO_LONG }
        validate(title.length < BoardValidator.TITLE_MAX_LENGTH) { BaseErrorCode.TITLE_TOO_LONG }
        validate(content.length < BoardValidator.CONTENT_MAX_LENGTH) { BaseErrorCode.CONTENT_TOO_LONG }
    }
}
