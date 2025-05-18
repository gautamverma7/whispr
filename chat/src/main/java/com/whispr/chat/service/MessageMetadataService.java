package com.whispr.chat.service;

import com.whispr.chat.model.MessageMetadata;
import com.whispr.chat.repository.MessageMetadataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageMetadataService {
    private final MessageMetadataRepository messageMetadataRepository;

    public MessageMetadataService(MessageMetadataRepository messageMetadataRepository) {
        this.messageMetadataRepository = messageMetadataRepository;
    }

    /**
     * Save metadata record for a message after its content has been stored.
     * @param chatRoomId      ID of the chat room
     * @param sender          Username of sender
     * @param messageContentId ID returned by Message Service
     */
    public MessageMetadata saveMetadata(String chatRoomId, String sender, String messageContentId) {
        MessageMetadata metadata = new MessageMetadata();
        metadata.setChatRoomId(chatRoomId);
        metadata.setSender(sender);
        metadata.setTimestamp(LocalDateTime.now());
        metadata.setMessageContentId(messageContentId);
        return messageMetadataRepository.save(metadata);
    }

    public List<MessageMetadata> getMessagesByChatRoom(String chatRoomId) {
        return messageMetadataRepository.findByChatRoomIdOrderByTimestampAsc(chatRoomId);
    }
}
