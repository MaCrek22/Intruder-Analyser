package com.intruderanalyser;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.extension.ExtensionUnloadingHandler;

public class IntruderAnalyserExtension implements BurpExtension, ExtensionUnloadingHandler {
    
    private WebSocketManager webSocketManager;

    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("Intruder Web Analyser");

        // Start the WebSocket server on a specific port
        int port = 8888; // You can make this configurable later
        webSocketManager = new WebSocketManager(port);
        webSocketManager.start();

        api.http().registerHttpHandler(new IntruderHttpHandler(api, webSocketManager));

        // Register a handler to shut down the server when the extension unloads
        api.extension().registerUnloadingHandler(this);
        
        api.logging().logToOutput("Intruder Web Analyser loaded.");
        api.logging().logToOutput("WebSocket server is listening on ws://localhost:" + port);
        api.logging().logToOutput("Open the index.html file to connect.");
    }

    @Override
    public void extensionUnloaded() {
        // Cleanly shut down the WebSocket server
        if (webSocketManager != null) {
            try {
                webSocketManager.stop();
                System.out.println("WebSocket server stopped.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}