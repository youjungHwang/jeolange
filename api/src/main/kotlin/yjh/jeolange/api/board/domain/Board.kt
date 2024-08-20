package yjh.jeolange.api.board.domain

import java.time.LocalDateTime

/**
 * 필드 var 사용 이유:
 * - 게시글의 제목(title)과 내용(content)은 게시글 수정(update) 시 변경이 필요한 상황
 * - 게시글 삭제 시간(deletedAt)은 게시글 삭제(delete) 시 기록이 필요한 상황
 * -> 생성자에서 var를 사용했지만, 클래스 외부에서는 setter를 직접 사용하지 않을 것!
 *
 * [참고]
 * - val: getter는 사용 가능하지만 setter는 없음
 * - var: getter와 setter 모두 사용 가능
 */
class Board(
    val id: Long = 0,
    var title: String,
    var content: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    var deletedAt: LocalDateTime? = null,
) {
    companion object {
        fun create(command: BoardCreateCommand): Board {
            return Board(
                title = command.title,
                content = command.content
            )
        }
    }

    fun update(command: BoardUpdateCommand) {
        this.title = command.title
        this.content = command.content
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }
}
