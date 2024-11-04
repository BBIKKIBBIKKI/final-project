package com.wearei.finalsamplecode.domain.chat.service;

import com.wearei.finalsamplecode.domain.chat.repository.ChatRepository;
import com.wearei.finalsamplecode.domain.chat.repository.ChatRoom;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {
    private final ChatRepository roomRepository;
    private final Map<String, String> sessionRoomMap = new ConcurrentHashMap<>();
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>(); // 세션 관리용 맵

    /**
     * /chat 으로 소켓연결 시 해당 메서드가 호출되어 세션을 저장
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 세션을 저장합니다.
        sessions.put(session.getId(), session);

        log.info("새로운 세션 연결: {}", session.getId());
    }

    /**
     * 채팅방을 나갔을 때 처리
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // 연결이 종료되면 세션 및 방에서 제거합니다.
        String sessionId = session.getId();
        String roomId = sessionRoomMap.get(sessionId);

        if (roomId != null) {
            ChatRoom room = roomRepository.findRoom(roomId);
            if (room != null) {
                room.leave(sessionId);  // 방에서 해당 세션 제거

                log.info("{} 가 방 {} 에서 나갔습니다.", sessionId, roomId);
            }
        }

        // 세션 정보 삭제
        sessions.remove(sessionId);
        sessionRoomMap.remove(sessionId);

        log.info("세션 종료: {}", sessionId);
    }

    /**
     * 클라이언트에서 메세지를 전송했을 때 해당 메서드가 실행되고
     * 해당 세션, 메시지를 인자로 받음
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {
        String message = textMessage.getPayload();
        String[] splitMessage = message.split(" ", 2);
        String command = splitMessage[0];

        if ("enter".equals(command)) {
            joinRoom(session, splitMessage[1]);
        } else if ("msg".equals(command)) {
            sendMessageAllSession(session, splitMessage[1]);
        } else {
            session.sendMessage(new TextMessage("잘못된 접근입니다."));
        }
    }

    private void joinRoom(WebSocketSession session, String roomId) throws IOException {
        ChatRoom room = roomRepository.findRoom(roomId);
        if (room == null) {
            session.sendMessage(new TextMessage("방이 존재하지 않습니다."));
        }

        room.join(session.getId());
        sessionRoomMap.put(session.getId(), roomId);

        log.info("{} 가 방 {} 에 입장했습니다.", session.getId(), roomId);
    }

    public void sendMessageAllSession(WebSocketSession session, String message) throws IOException {
        String roomId = sessionRoomMap.get(session.getId());
        if (roomId == null) {
            session.sendMessage(new TextMessage("먼저 방에 입장하세요."));
            return;
        }

        ChatRoom room = roomRepository.findRoom(roomId);
        for (String participantId : room.getChatRoomUsers()) {
            WebSocketSession participantSession = sessions.get(participantId); // 세션을 맵에서 찾음
            if (participantSession != null && participantSession.isOpen()) {
                participantSession.sendMessage(new TextMessage("[" + roomId + "] " + message));
            }
        }
    }
}