package yjh.jeolange.api.board.presentation.response

import yjh.jeolange.api.board.domain.Board
import java.time.LocalDateTime

data class BoardResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null,
) {
    companion object {
        fun from(board: Board): BoardResponse {
            return BoardResponse(
                id = board.id,
                title = board.title,
                content = board.content,
                createdAt = board.createdAt ?: LocalDateTime.now(),
                updatedAt = board.updatedAt,
                deletedAt = board.deletedAt
            )
        }
    }
}
