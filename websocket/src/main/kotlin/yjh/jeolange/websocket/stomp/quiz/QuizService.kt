package yjh.jeolange.websocket.stomp.quiz

class QuizService {
    private var currentQuizIndex = 0
    private val quizzes = listOf(
        "문제 1: 클래스는 무엇인가요?",
        "문제 2: 객체지향설계 4가지 원칙은 무엇인가요?"
    )

    fun getCurrentQuiz(): String {
        return quizzes[currentQuizIndex]
    }

    fun checkAnswer(answer: String): Boolean {
        // TODO: 정답 확인 로직 필요
        return true // 가정: 우선 다 맞다고 함
    }

    fun getNextQuiz(): String {
        currentQuizIndex++
        return if (currentQuizIndex < quizzes.size) {
            quizzes[currentQuizIndex]
        } else {
            resetQuiz() // 퀴즈를 리셋하는 메서드
        }
    }

    private fun resetQuiz(): String {
        currentQuizIndex = 0
        return "준비된 퀴즈가 모두 소진되었습니다! 다시 시작합니다."
    }

}
