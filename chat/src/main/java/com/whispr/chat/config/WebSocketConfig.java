package com.whispr.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * Configures STOMP over WebSocket with a simple in-memory broker.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");          // PUBLISH to "/topic/..."
        config.setApplicationDestinationPrefixes("/app");  // SUBSCRIBE client sends to "/app/..."
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Clients will connect to ws://<host>:port/ws-chat
        registry.addEndpoint("/ws-chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
