package com.socialmedia.kinchana.payload.response;

import java.time.LocalDateTime;

public class MessageResponse {
    private String content;
    private String timestamp;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setDateTime(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

//    public LocalDateTime getDateTime() {
//        return dateTime;
//    }
}
