package com.wearei.finalsamplecode.domain.chat.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class ChatRepository {
    private final Map<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

    public ChatRoom createRoom(String roomId) {
        return chatRooms.put(roomId, new ChatRoom(roomId));
    }

    public ChatRoom findRoom(String roomId) {
        return chatRooms.get(roomId);
    }
}