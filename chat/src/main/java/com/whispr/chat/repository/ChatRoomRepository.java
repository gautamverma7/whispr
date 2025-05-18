package com.whispr.chat.repository;

import com.whispr.chat.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    // Find all chat rooms where a given user participates
    List<ChatRoom> findByParticipantsContaining(String participant);
}
