package yjh.jeolange.api.board.application.port

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import yjh.jeolange.api.board.domain.Board

/**
 * BoardRepository 인터페이스는 비즈니스 계층에서 도메인 객체를 반환하도록 설계됨.
 *
 * 다른 서비스에서 특정 서비스의 메서드를 호출할 때 도메인 객체를 반환받으면,
 * 후속 작업이 편리해지고 코드의 재사용성이 향상됨.
 */
interface BoardRepository {
    fun save(board: Board): Board

    fun findByIdOrNull(id: Long): Board?

    fun findAll(pageable: Pageable): Page<Board>
}
