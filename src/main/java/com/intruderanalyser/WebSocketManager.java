package com.intruderanalyser;

import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Map;

public class WebSocketManager extends WebSocketServer {

    private final Gson gson = new Gson();

    public WebSocketManager(int port) {
        super(new InetSocketAddress(port));
    }

    // Method to broadcast a message to all connected clients
    public void broadcastMessage(String type, Object data) {
        Map<String, Object> messageMap = Map.of(
                "type", type,
                "data", data
        );
        String jsonMessage = gson.toJson(messageMap);
        broadcast(jsonMessage);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New client connected: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Client disconnected: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // We are only broadcasting, not receiving messages, so this can be empty.
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started on port: " + getPort());
    }
}