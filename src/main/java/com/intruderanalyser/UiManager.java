package com.intruderanalyser;

import burp.api.montoya.MontoyaApi;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class UiManager {
    private final MontoyaApi api;
    private final Path uiHtmlPath;

    public UiManager(MontoyaApi api, Path storagePath) {
        this.api = api;
        this.uiHtmlPath = storagePath.resolve("index.html");
    }

    public void createUiFilesIfNeeded() {
        if (!Files.exists(uiHtmlPath)) {
            try (InputStream is = getClass().getResourceAsStream("/ui/index.html")) {
                if (is == null) {
                    api.logging().logToError("Could not find index.html in resources.");
                    return;
                }
                Files.copy(is, uiHtmlPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                api.logging().logToError("Failed to create UI file: " + e.getMessage());
            }
        }
    }
    
    public Path getUiHtmlPath() {
        return uiHtmlPath;
    }
}