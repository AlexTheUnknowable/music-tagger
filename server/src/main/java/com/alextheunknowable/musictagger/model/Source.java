package com.alextheunknowable.musictagger.model;

public class Source {
    private int id;
    private String name;
    private String imageUrl;
    private int uploaderId;

    public Source() {}
    public Source(String name, String imageUrl, int uploaderId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.uploaderId = uploaderId;
    }
    public Source(int id, String name, String imageUrl, int uploaderId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.uploaderId = uploaderId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getUploaderId() {
        return uploaderId;
    }
    public void setUploaderId(int UserId) {
        this.uploaderId = uploaderId;
    }
}
