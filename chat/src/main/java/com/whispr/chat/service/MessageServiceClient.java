package com.whispr.chat.service;

import com.whispr.chat.dto.ChatMessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * REST client to call the Message Service (MongoDB) for saving/retrieving message bodies.
 */
@Service
public class MessageServiceClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${message.service.url}")
    private String messageServiceUrl;

    /**
     * Posts the ChatMessageDTO to Message Service, returns the MongoDB-generated ID.
     */
    public String saveMessageContent(ChatMessageDTO dto) {
        String url = messageServiceUrl + "/api/messages";
        // The Message Service endpoint returns an object that includes the created ID
        @SuppressWarnings("unchecked")
        var response = restTemplate.postForObject(url, dto, java.util.Map.class);
        // Assuming response JSON is { "id": "<mongoId>" }
        if (response != null && response.get("id") != null) {
            return response.get("id").toString();
        }
        throw new RuntimeException("Failed to save message content");
    }
}