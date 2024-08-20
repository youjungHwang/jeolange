package yjh.jeolange.api.common

import org.springframework.http.HttpStatus
import yjh.jeolange.api.board.domain.BoardValidator.Companion.CONTENT_MAX_LENGTH
import yjh.jeolange.api.board.domain.BoardValidator.Companion.TITLE_MAX_LENGTH
import yjh.jeolange.api.board.domain.BoardValidator.Companion.TITLE_MIN_LENGTH

enum class BaseErrorCode(
    val httpStatus: HttpStatus,
    val code: Int,
    val message: String,
) {
    // 200
    SUCCESS(HttpStatus.OK, 2000, "요청 성공"),

    // 400 : title 필드
    NOT_EMPTY_TITLE(HttpStatus.BAD_REQUEST, 4001, "게시글 제목은 공백일 수 없습니다."),
    TITLE_TOO_LONG(HttpStatus.BAD_REQUEST, 4002, "게시글 제목의 길이는 $TITLE_MIN_LENGTH 자 이상 $TITLE_MAX_LENGTH 자 이하 여야 합니다."),

    // 400 : content 필드
    NOT_EMPTY_CONTENT(HttpStatus.BAD_REQUEST, 40011, "게시글 내용은 공백일 수 없습니다."),
    CONTENT_TOO_LONG(HttpStatus.BAD_REQUEST, 40012, "내용의 길이는 $CONTENT_MAX_LENGTH 자 이하여야 합니다."),

    // 404
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, 4041, "게시글을 찾을 수 없습니다"),

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "서버 내부 에러"),
}
