package com.whispr.chat.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message_metadata")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatRoomId;
    private String sender;
    private LocalDateTime timestamp;

    private String messageContentId;  // ID returned from Message Service (MongoDB)
}