package com.whispr.chat.service;

import com.whispr.chat.model.ChatRoom;
import com.whispr.chat.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    /**
     * Finds an existing ChatRoom with exactly these participants,
     * or creates & returns a new one.
     */
    public ChatRoom getOrCreateChatRoom(Set<String> participants) {
        // Attempt to find an existing room that has exactly the same set
        List<ChatRoom> potentialRooms =
                chatRoomRepository.findByParticipantsContaining(participants.iterator().next());

        for (ChatRoom room : potentialRooms) {
            if (room.getParticipants().equals(participants)) {
                return room;
            }
        }

        // Otherwise, create a new ChatRoom
        ChatRoom newRoom = new ChatRoom();
        newRoom.setId(UUID.randomUUID().toString());
        newRoom.setParticipants(participants);
        return chatRoomRepository.save(newRoom);
    }

    public List<ChatRoom> getChatRoomsForUser(String username) {
        return chatRoomRepository.findByParticipantsContaining(username);
    }
}
