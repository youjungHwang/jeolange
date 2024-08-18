package yjh.jeolange.websocket.stomp

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils

/**
 * @MessageMapping("/chatting-message")
 *    StompWebSocketConfig > config.setApplicationDestinationPrefixes("/app")라고 설정되어 있으므로,
 *    클라이언트가 app/chatting-message 로 보낼 때 처리됩니다.
 */
@Controller
class StompController {

    @MessageMapping("/chatting-message") // 클라이언트의 메시지를 처리할 경로
    @SendTo("/topic/chatting") // 반환된 메시지를 구독 중인 모든 클라이언트에게 전송
    fun chatting(chattingMessage: ChattingMessage): ChattingMessageResponse {
        println("StompController: 메시지를 받았습니다 >>> ${chattingMessage.message}")

        // HTML 이스케이프 처리를 통해 XSS 공격 방지
        val escapedMessage = HtmlUtils.htmlEscape(chattingMessage.message)

        // 클라이언트에게 전달할 응답 객체를 반환
        return ChattingMessageResponse(escapedMessage)
    }
}