package yjh.jeolange.websocket.stomp

data class ChattingMessage(val message: String = "")

data class ChattingMessageResponse(val content: String)