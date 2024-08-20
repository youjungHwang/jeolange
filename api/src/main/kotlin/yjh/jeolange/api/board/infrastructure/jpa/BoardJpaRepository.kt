package yjh.jeolange.api.board.infrastructure.jpa

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface BoardJpaRepository : JpaRepository<BoardEntity, Long> {

    @Query("SELECT b FROM BoardEntity b WHERE b.deletedAt IS NULL AND b.id = :id")
    fun findByIdOrNull(@Param("id") id: Long): BoardEntity?

    @Query("SELECT b FROM BoardEntity b WHERE b.deletedAt IS NULL")
    override fun findAll(pageable: Pageable): Page<BoardEntity>
}
