package com.socialmedia.kinchana.payload.response;

public class ImageStockResponse {
    private String image;
    private String timestamp;

    public ImageStockResponse(String image, String timestamp) {
        this.image = image;
        this.timestamp = timestamp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
