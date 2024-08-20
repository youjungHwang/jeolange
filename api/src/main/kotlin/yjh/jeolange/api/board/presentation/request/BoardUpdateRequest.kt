package yjh.jeolange.api.board.presentation.request

import yjh.jeolange.api.board.domain.BoardUpdateCommand

data class BoardUpdateRequest(
    val title: String,
    val content: String,
)

fun BoardUpdateRequest.toCommand() = BoardUpdateCommand(
    title = title,
    content = content
)
