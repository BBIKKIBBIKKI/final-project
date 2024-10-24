package com.wearei.finalsamplecode.domain.chat.repository;

import lombok.Getter;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private final String roomId;

    // 방에 입장한 사람들
    private final Set<String> chatRoomUsers = new HashSet<>();

    public ChatRoom(String roomId) {
        this.roomId = roomId;
    }

    public void join(String sessionId) {
        chatRoomUsers.add(sessionId);
    }

    public void leave(String sessionId) {
        chatRoomUsers.remove(sessionId);
    }
}