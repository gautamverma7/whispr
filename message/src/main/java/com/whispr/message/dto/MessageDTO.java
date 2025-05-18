package com.whispr.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO used by both Chat Service (when saving) and by clients (when retrieving).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
    private String id;            // MongoDB ID, set by server
    private String chatRoomId;
    private String sender;
    private String content;
    private LocalDateTime sentAt; // ISO-8601 timestamp
}
