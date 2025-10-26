package com.intruderanalyser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigManager {
    // The filename remains the same
    private static final String CONFIG_FILE_NAME = "config.properties";
    private static final String KEY_OUTPUT_DIR = "output.directory";

    private final Properties properties;
    private final Path configFilePath;
    
    // The base storage path is no longer needed as a field
    // private final Path baseStoragePath;

    public ConfigManager() {
        // FIX: Hardcode the base path to "D:\nieco"
        // Note the double backslashes \\ to escape the backslash character in a Java String
        Path configDirectory = Paths.get("d:\\Citadelo\\Burp\\Extensions\\intruder_analyzer");
        
        this.configFilePath = configDirectory.resolve(CONFIG_FILE_NAME);
        this.properties = new Properties();
        
        // Pass the new static path to the helper method
        createDirectoryAndDefaultConfigIfNeeded(configDirectory);
        loadProperties();
    }

    // The helper method now takes the path as an argument
    private void createDirectoryAndDefaultConfigIfNeeded(Path configDirectory) {
        try {
            // Create the D:\nieco directory if it doesn't exist
            Files.createDirectories(configDirectory);

            if (!Files.exists(configFilePath)) {
                Properties defaultConfig = new Properties();
                // The output.directory in the generated file will still be empty by default,
                // but the plugin's default behavior will now be based on the hardcoded path.
                defaultConfig.setProperty(KEY_OUTPUT_DIR, "");
                
                try (FileOutputStream out = new FileOutputStream(configFilePath.toFile())) {
                    defaultConfig.store(out, "Intruder Analyser Configuration");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadProperties() {
        // This logic doesn't change
        try (FileInputStream in = new FileInputStream(configFilePath.toFile())) {
            properties.load(in);
        } catch (IOException e) {
            // If the file can't be read (e.g., D: drive doesn't exist), this will fail silently.
            // A more robust plugin would log this error to the Burp UI.
        }
    }

    public Path getOutputDirectory() {
        String configuredPath = properties.getProperty(KEY_OUTPUT_DIR);
        
        if (configuredPath == null || configuredPath.trim().isEmpty()) {
            // If output.directory is empty in the config file, default to D:\nieco
            return Paths.get("D:\\nieco");
        }
        
        return Paths.get(configuredPath.trim());
    }
}