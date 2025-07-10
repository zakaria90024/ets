package com.sasoftbd.Employee_Tracking_System.jwtauth.MyWebSocketClient;


import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyWebSocketHandler extends TextWebSocketHandler {

    private static final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("🟢 Client connected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("📥 Received: " + message.getPayload());
        // Echo back or broadcast
        session.sendMessage(new TextMessage("Server received: " + message.getPayload()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        System.out.println("🔴 Client disconnected: " + session.getId());
    }

    // 🔔 Custom method to push notification to all clients
    public static void sendToAllClients(String title, String body) throws IOException {
        String json = String.format("{\"title\":\"%s\", \"body\":\"%s\"}", title, body);
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {

                session.sendMessage(new TextMessage(json));

            }
        }
    }
}
