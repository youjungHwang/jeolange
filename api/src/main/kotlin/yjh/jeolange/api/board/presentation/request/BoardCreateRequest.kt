package yjh.jeolange.api.board.presentation.request

import yjh.jeolange.api.board.domain.BoardCreateCommand

data class BoardCreateRequest(
    val title: String,
    val content: String,
)

/**
 * 확장 함수: BoardCreateRequest를 BoardCreateCommand로 변환하여 비즈니스 로직에서 사용
 *
 * - DTO와 Command를 분리하여 유지보수성을 높임
 * - RoomCreateRequest는 클라이언트 요구에 따라 변경 가능하며,
 *   변경 시 해당 파일만 수정하면 됨
 * - Command는 비즈니스 로직을 처리하는 도메인 객체로,
 *   유효성 검사 등의 비즈니스 규칙을 적용할 수 있음
 */
fun BoardCreateRequest.toCommand() = BoardCreateCommand(
    title = title,
    content = content
)
