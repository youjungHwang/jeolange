/**
 * StompJs 클라이언트 생성
 * @type {StompJs.Client}
 */
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/stomp', // 소켓 서버와 연결을 시도
    reconnectDelay: 200,
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/chatting', (received_message) => {
        console.log("> Received message: " + received_message.body)
        showChatting(JSON.parse(received_message.body).content);
    });
};
stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};
stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#chatting").html("");
    if (connected) {
        $("#chatting").append("<tr><td>>>> Connected Chatting Server !!!</td></tr>");
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    // WebSocket 연결을 종료하고, 현재 구독 중인 모든 토픽에서 자동으로 구독을 취소
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    console.log("> Send message : " + $("#chatting-message").val());
    stompClient.publish({
        destination: "/app/chatting-message",
        body: JSON.stringify({'message': $("#chatting-message").val()})
    });
    $("#chatting-message").val(""); // 입력 필드 비우기
}

function showChatting(message) {
    $("#chatting").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendMessage());
});
