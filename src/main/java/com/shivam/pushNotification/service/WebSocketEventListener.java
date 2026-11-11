package com.shivam.pushNotification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component 
public class WebSocketEventListener {
    private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        if (event.getUser() != null) {
            log.info("WebSocket user connected: {}", event.getUser().getName());
        } else {
            log.info("WebSocket anonymous session connected: {}", event.getMessage());
        }
    }

    @EventListener
    public void handleSessionDisconnected(SessionDisconnectEvent event) {
        if (event.getUser() != null) {
            log.info("WebSocket user disconnected: {} (sessionId: {})", event.getUser().getName(), event.getSessionId());
        } else {
            log.info("WebSocket session disconnected: {}", event.getSessionId());
        }
    }
}

