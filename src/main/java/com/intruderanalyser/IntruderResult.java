package com.intruderanalyser;

public class IntruderResult {
    // No changes to fields, but constructor order is updated for clarity
    private final int id;
    private final String timestamp;
    private final String url;
    private final short statusCode;
    private final int responseLength; 
    private final int totalResponseLength;
    private final long responseTime; 
    private final String payload; 
    private final String requestBase64;
    private final String responseBase64;

    public IntruderResult(int id, String timestamp, String url, short statusCode, int responseLength, int totalResponseLength, int contentTypeLength, long responseTime, String payload, String requestBase64, String responseBase64) {
        this.id = id;
        this.timestamp = timestamp;
        this.url = url;
        this.statusCode = statusCode;
        this.responseLength = responseLength;
        this.totalResponseLength = totalResponseLength;
        this.responseTime = responseTime;
        this.payload = payload;
        this.requestBase64 = requestBase64;
        this.responseBase64 = responseBase64;
    }
}