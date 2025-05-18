package com.whispr.message.repository;

import com.whispr.message.model.MessageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MessageRepository extends MongoRepository<MessageDocument, String> {
    List<MessageDocument> findByChatRoomIdOrderBySentAtAsc(String chatRoomId);
}
