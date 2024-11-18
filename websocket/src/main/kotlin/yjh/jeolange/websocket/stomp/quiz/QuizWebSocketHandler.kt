package yjh.jeolange.websocket.stomp.quiz

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class QuizWebSocketHandler : TextWebSocketHandler() {
    // 웹소켓 세션을 저장할 리스트
    private val sessions = mutableListOf<WebSocketSession>()
    private val quizService = QuizService()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session) // 세션 추가
        sendCurrentQuiz(session) // 현재 퀴즈 전송
    }

    // 클라이언트로부터 텍스트 메시지를 수신했을 때 호출되는 메서드
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val answer = message.payload // 수신한 메시지에서 답변 추출
        val isCorrect = quizService.checkAnswer(answer) // 답변 정답 여부 확인

        // 정답 여부에 따라 처리
        if (isCorrect) {
            val nextQuiz = quizService.getNextQuiz() // 다음 퀴즈 가져오기
            sendMessageToAll(TextMessage(nextQuiz)) // 모든 클라이언트에 다음 퀴즈 전송
        } else {
            sendMessageToAll(TextMessage("틀린 답변입니다."))
        }
    }

    // 특정 클라이언트에게 현재 퀴즈를 전송하는 메서드
    private fun sendCurrentQuiz(session: WebSocketSession) {
        val currentQuiz = quizService.getCurrentQuiz() // 현재 퀴즈 가져오기
        session.sendMessage(TextMessage(currentQuiz)) // 클라이언트에 현재 퀴즈 전송
    }

    // 모든 연결된 클라이언트에게 메시지를 전송하는 메서드
    private fun sendMessageToAll(message: TextMessage) {
        sessions.filter { it.isOpen }.forEach { session ->
            session.sendMessage(message) // 세션이 열려있으면, 메세지 전송
        }
    }

}
