package yjh.jeolange.api.board.infrastructure.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import yjh.jeolange.api.board.domain.Board
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Table(name = "board")
@Entity
class BoardEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0,

    @Column(nullable = false)
    private val title: String,

    @Column(nullable = false)
    private val content: String,

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    private val updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    private val deletedAt: LocalDateTime? = null,
) {

    companion object {
        fun from(board: Board): BoardEntity {
            return BoardEntity(
                id = board.id,
                title = board.title,
                content = board.content,
                createdAt = board.createdAt,
                updatedAt = board.updatedAt,
                deletedAt = board.deletedAt
            )
        }
    }

    fun toModel(): Board {
        return Board(
            id = this.id,
            title = this.title,
            content = this.content,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            deletedAt = this.deletedAt
        )
    }
}
