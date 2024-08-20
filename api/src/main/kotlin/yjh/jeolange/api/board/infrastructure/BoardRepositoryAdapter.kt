package yjh.jeolange.api.board.infrastructure

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import yjh.jeolange.api.board.application.port.BoardRepository
import yjh.jeolange.api.board.domain.Board
import yjh.jeolange.api.board.infrastructure.jooq.BoardJooqRepository
import yjh.jeolange.api.board.infrastructure.jpa.BoardEntity
import yjh.jeolange.api.board.infrastructure.jpa.BoardJpaRepository

/**
 * BoardRepositoryAdapter는 게시판 데이터에 접근하기 위한 어댑터로,
 * BoardRepository 인터페이스를 상속받아 구현되었습니다.
 * 해당 인터페이스는 application.port 패키지에 위치합니다.
 *
 * 포트와 어댑터 아키텍처를 적용하여 비즈니스 로직과 인프라를 분리함으로써,
 * 데이터 접근 방식(JPA, jOOQ, QueryDSL)을 유연하게 변경할 수 있습니다.
 * 사용자는 구체적인 구현에 대해 알 필요 없이 인터페이스를 통해 필요한 기능을 이용할 수 있습니다.
 */
@Repository
class BoardRepositoryAdapter(
    private val boardJpaRepository: BoardJpaRepository,
    private val boardJooqRepository: BoardJooqRepository,
) : BoardRepository {
    override fun save(board: Board): Board {
        return boardJpaRepository.save(BoardEntity.from(board)).toModel()
    }

    override fun findByIdOrNull(id: Long): Board? {
        return boardJpaRepository.findByIdOrNull(id)?.toModel()
    }

    override fun findAll(pageable: Pageable): Page<Board> {
        return boardJpaRepository.findAll(pageable).map { it.toModel() }
    }
}
