package com.example.servehumanity.response;

public class ImageResponse {
    String status;
    File file;

    public ImageResponse(String status, File file) {
        this.status = status;
        this.file = file;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
