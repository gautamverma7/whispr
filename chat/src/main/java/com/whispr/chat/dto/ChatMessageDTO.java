package com.whispr.chat.dto;

import lombok.Data;

/**
 * Data Transfer Object used for sending/receiving chat messages over WebSocket
 * and also sent to Message Service for persistence.
 */
@Data
public class ChatMessageDTO {
    private String chatRoomId;
    private String sender;
    private String content;
    private String timestamp;  // Can be null on send; client or server may fill ISO-8601 string
}
