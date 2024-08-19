package yjh.jeolange.websocket.stomp

import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.web.socket.messaging.SessionConnectedEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import org.springframework.web.socket.messaging.SessionSubscribeEvent
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent

/**
 * WebSocket 세션과 관련된 이벤트를 처리하며,클라이언트의 연결 상태, 구독 상태 등을 모니터링합니다.
 */
@Component
class StompWebSocketEventHandler {

    @EventListener
    fun handleWebSocketSessionConnectEventListener(event: SessionConnectEvent) {
        println("클라이언트가 WebSocket 세션에 연결을 시도하고 있습니다...")
    }

    @EventListener
    fun handleWebSocketSessionConnectedEventListener(event: SessionConnectedEvent) {
        println("클라이언트가 WebSocket 세션에 성공적으로 연결되었습니다. 메시지: ${event.message}")
    }

    @EventListener
    fun handleWebSocketSessionSubscribeEventListener(event: SessionSubscribeEvent) {
        val headers = StompHeaderAccessor.wrap(event.message)
        println("클라이언트가 토픽 '${headers.destination}'을 구독하였습니다.")
    }

    @EventListener
    fun handleWebSocketSessionUnsubscribeEventListener(event: SessionUnsubscribeEvent) {
        val headers = StompHeaderAccessor.wrap(event.message)
        println("클라이언트가 토픽 '${headers.destination}'의 구독을 취소하였습니다.")
    }

    @EventListener
    fun handleWebSocketSessionDisconnectEventListener(event: SessionDisconnectEvent) {
        println("클라이언트와의 WebSocket 연결이 종료되었습니다.")
    }
}
