## Spring Boot + WebSocket

### STOMP를 활용한 WebSocket 사용하기

#### 파일 설명
- **StompWebSocketConfig.kt**: WebSocket과 STOMP 프로토콜의 설정을 담당합니다.
- **StompWebSocketEventHandler.kt**: WebSocket 세션과 관련된 이벤트를 처리하여 연결 상태를 모니터링합니다.

#### 의존성 추가
 ```
    "org.springframework.boot:spring-boot-starter-websocket"
 ```

#### 실행 방법
1. **스프링 부트 애플리케이션 실행**
2. **브라우저에서 애플리케이션 접속**
    - `http://localhost:8080`을 입력하여 애플리케이션에 접속합니다.
    - 채팅 인터페이스가 로드되면 메시지를 입력하고 전송하여 다른 클라이언트와 실시간으로 소통할 수 있습니다.

#### 참고 사항
- WebSocket 연결이 성공하면 콘솔에서 연결 상태를 확인할 수 있습니다.
- 클라이언트 간의 메시지는 STOMP 프로토콜을 통해 전송됩니다.
