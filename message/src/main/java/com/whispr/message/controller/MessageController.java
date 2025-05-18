package com.whispr.message.controller;

import com.whispr.message.dto.MessageDTO;
import com.whispr.message.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Exposes:
 *   POST   /api/messages       → Save new message, returns { "id": "<mongoId>" }
 *   GET    /api/messages/{roomId} → List all messages in that room
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody MessageDTO dto) {
        // Save the message and get back a DTO with ID
        MessageDTO saved = messageService.saveMessage(dto);
        // Return only the ID in JSON: { "id": "<saved.getId()>" }
        return ResponseEntity.ok(Collections.singletonMap("id", saved.getId()));
    }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable String chatRoomId) {
        List<MessageDTO> messages = messageService.getMessagesByChatRoom(chatRoomId);
        return ResponseEntity.ok(messages);
    }
}
