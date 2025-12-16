package com.alextheunknowable.musictagger.model;

public class Artist {
    private int id;
    private String name;
    private int uploaderId;

    public Artist() {}
    public Artist(int id, String name, int uploaderId) {
        this.id = id;
        this.name = name;
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
    public int getUploaderId() {
        return uploaderId;
    }
    public void setUploaderId(int uploadedByUser) {
        this.uploaderId = uploaderId;
    }
}
