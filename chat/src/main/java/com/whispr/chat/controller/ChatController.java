package com.whispr.chat.controller;

import com.whispr.chat.dto.ChatMessageDTO;
import com.whispr.chat.service.MessageMetadataService;
import com.whispr.chat.service.MessageServiceClient;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Listens for incoming messages at "/app/sendMessage", and broadcasts to "/topic/messages".
 */
@Controller
public class ChatController {

    private final MessageMetadataService metadataService;
    private final MessageServiceClient messageServiceClient;

    public ChatController(MessageMetadataService metadataService, MessageServiceClient messageServiceClient) {
        this.metadataService = metadataService;
        this.messageServiceClient = messageServiceClient;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessageDTO sendMessage(ChatMessageDTO message) {
        // 1) Save message content into Message Service (MongoDB) → returns Mongo ID
        String messageContentId = messageServiceClient.saveMessageContent(message);

        // 2) Save metadata locally (Azure SQL) with that Mongo ID
        metadataService.saveMetadata(
                message.getChatRoomId(),
                message.getSender(),
                messageContentId
        );

        // 3) Return the same DTO (timestamp can be client-set or added later)
        return message;
    }
}
