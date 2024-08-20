package yjh.jeolange.api.board.application

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import yjh.jeolange.api.board.application.port.BoardRepository
import yjh.jeolange.api.board.domain.Board
import yjh.jeolange.api.board.domain.BoardCreateCommand
import yjh.jeolange.api.board.domain.BoardUpdateCommand
import yjh.jeolange.api.common.BaseErrorCode
import yjh.jeolange.api.common.BaseException

@Transactional(readOnly = true)
@Service
class BoardService(
    private val boardRepository: BoardRepository,
) {

    @Transactional
    fun create(command: BoardCreateCommand): Long {
        val board = Board.create(command)
        return boardRepository.save(board).id
    }

    @Transactional
    fun update(id: Long, command: BoardUpdateCommand): Long {
        val board = retrieve(id)
        board.update(command)
        return boardRepository.save(board).id
    }

    @Transactional
    fun delete(id: Long): Long {
        val board = retrieve(id)
        board.delete()
        return boardRepository.save(board).id
    }

    fun retrieve(id: Long): Board {
        return boardRepository.findByIdOrNull(id) ?: throw BaseException(BaseErrorCode.NOT_FOUND_POST)
    }

    fun retrieveAll(pageable: Pageable): Page<Board> {
        return boardRepository.findAll(pageable)
    }
}
