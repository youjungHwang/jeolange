package yjh.jeolange.websocket.stomp

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

/**
 * WebSocket과 STOMP 프로토콜을 설정을 담당하며 클라이언트가 WebSocket에 연결하도록 설정합니다.
 */
@EnableWebSocketMessageBroker
@Configuration
class StompWebSocketConfig : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        // 간단한 메시지 브로커를 활성화하고, "/topic"으로 시작하는 주제를 구독 가능하게 설정합니다.
        config.enableSimpleBroker("/topic")
        // 클라이언트가 서버로 메시지를 보낼 때 사용할 접두사를 설정합니다.
        config.setApplicationDestinationPrefixes("/app")
    }

    // STOMP 엔드포인트를 등록합니다. 클라이언트는 이 엔드포인트를 통해 WebSocket 연결을 설정합니다.
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry
            // 클라이언트가 연결할 엔드포인트 URL을 "/stomp"로 설정합니다.
            .addEndpoint("/stomp")
            .setAllowedOriginPatterns("*")
    }

}