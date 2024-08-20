package yjh.jeolange.api.board.presentation

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import yjh.jeolange.api.board.application.BoardService
import yjh.jeolange.api.board.presentation.request.BoardCreateRequest
import yjh.jeolange.api.board.presentation.request.BoardUpdateRequest
import yjh.jeolange.api.board.presentation.request.toCommand
import yjh.jeolange.api.board.presentation.response.BoardResponse
import yjh.jeolange.api.common.response.Response

@RequestMapping("/v1/boards")
@RestController
class BoardController(
    private val boardService: BoardService,
) {

    @PostMapping
    fun create(
        @RequestBody request: BoardCreateRequest,
    ): ResponseEntity<Response<Long>> {
        return ResponseEntity.ok(Response(data = boardService.create(request.toCommand())))
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: BoardUpdateRequest,
    ): ResponseEntity<Response<Long>> {
        return ResponseEntity.ok(Response(data = boardService.update(id, request.toCommand())))
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ): ResponseEntity<Response<Long>> {
        return ResponseEntity.ok(Response(data = boardService.delete(id)))
    }

    @GetMapping("/{id}")
    fun retrieve(
        @PathVariable id: Long,
    ): ResponseEntity<Response<BoardResponse>> {
        val response = BoardResponse.from(boardService.retrieve(id))
        return ResponseEntity.ok(Response(data = response))
    }

    @GetMapping
    fun retrieveAll(
        pageable: Pageable,
    ): ResponseEntity<Response<Page<BoardResponse>>> {
        val response = boardService.retrieveAll(pageable).map { BoardResponse.from(it) }
        return ResponseEntity.ok(Response(data = response))
    }
}
