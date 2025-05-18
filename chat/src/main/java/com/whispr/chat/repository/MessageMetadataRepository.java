package com.whispr.chat.repository;

import com.whispr.chat.model.MessageMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageMetadataRepository extends JpaRepository<MessageMetadata, Long> {
    // Fetch metadata for a chat room in ascending timestamp order
    List<MessageMetadata> findByChatRoomIdOrderByTimestampAsc(String chatRoomId);
}
