package com.intruderanalyser;

import burp.api.montoya.MontoyaApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataStore {
    private final MontoyaApi api;
    private final Path jsonFilePath;
    private final Path storagePath;
    private final List<IntruderResult> results;
    private final Gson gson;

    public DataStore(MontoyaApi api, Path baseOutputPath) {
        this.api = api;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        
        String projectName = api.project().name();
        if (projectName == null || projectName.isBlank()) {
            projectName = "untitled-project";
        }
        
        String safeDirName = projectName.replaceAll("[^a-zA-Z0-9.-]", "_");
        
        this.storagePath = baseOutputPath.resolve(safeDirName);
        this.jsonFilePath = storagePath.resolve("intruder_data.json");
        
        this.results = Collections.synchronizedList(new ArrayList<>());
        
        try {
            Files.createDirectories(storagePath);
        } catch (IOException e) {
            api.logging().logToError("Failed to create storage directory: " + e.getMessage());
        }

        loadExistingData();
    }

    private void loadExistingData() {
        if (Files.exists(jsonFilePath)) {
            try (Reader reader = new FileReader(jsonFilePath.toFile())) {
                Type resultListType = new TypeToken<ArrayList<IntruderResult>>(){}.getType();
                List<IntruderResult> loadedResults = gson.fromJson(reader, resultListType);
                if (loadedResults != null) {
                    results.addAll(loadedResults);
                }
            } catch (IOException e) {
                api.logging().logToError("Could not load existing data: " + e.getMessage());
            }
        }
    }
    
    public void addResult(IntruderResult result) {
        results.add(result);
        saveToFile();
    }

    private synchronized void saveToFile() {
        try (Writer writer = new FileWriter(jsonFilePath.toFile())) {
            gson.toJson(results, writer);
        } catch (IOException e) {
            api.logging().logToError("Failed to write to JSON file: " + e.getMessage());
        }
    }
    
    public Path getJsonFilePath() {
        return jsonFilePath;
    }

    public Path getStoragePath() {
        return storagePath;
    }
}