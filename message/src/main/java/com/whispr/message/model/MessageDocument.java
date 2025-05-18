package com.whispr.message.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDocument {
    @Id
    private String id;             // MongoDB-generated ID

    private String chatRoomId;     // ID of chat room (UUID from Chat Service)
    private String sender;         // Username of sender
    private String content;        // Text content of message
    private LocalDateTime sentAt;  // Timestamp when saved
}
