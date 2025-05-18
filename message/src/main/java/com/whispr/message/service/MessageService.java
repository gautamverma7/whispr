package com.whispr.message.service;

import com.whispr.message.dto.MessageDTO;
import com.whispr.message.model.MessageDocument;
import com.whispr.message.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Save a new message into MongoDB. Returns a MessageDTO with the generated ID.
     */
    public MessageDTO saveMessage(MessageDTO dto) {
        // Build a MessageDocument from DTO
        MessageDocument doc = MessageDocument.builder()
                .chatRoomId(dto.getChatRoomId())
                .sender(dto.getSender())
                .content(dto.getContent())
                .sentAt(LocalDateTime.now())
                .build();

        MessageDocument saved = messageRepository.save(doc);

        // Build a new DTO including generated ID and sentAt
        return MessageDTO.builder()
                .id(saved.getId())
                .chatRoomId(saved.getChatRoomId())
                .sender(saved.getSender())
                .content(saved.getContent())
                .sentAt(saved.getSentAt())
                .build();
    }

    /**
     * Retrieve all messages for a given chatRoomId, sorted by sentAt ascending.
     */
    public List<MessageDTO> getMessagesByChatRoom(String chatRoomId) {
        List<MessageDocument> docs = messageRepository.findByChatRoomIdOrderBySentAtAsc(chatRoomId);
        return docs.stream()
                .map(doc -> MessageDTO.builder()
                        .id(doc.getId())
                        .chatRoomId(doc.getChatRoomId())
                        .sender(doc.getSender())
                        .content(doc.getContent())
                        .sentAt(doc.getSentAt())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
