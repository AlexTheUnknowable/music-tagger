package com.alextheunknowable.musictagger.model;

public class Tag {
    private int id;
    private String name;
    private int uploadedByUserId;

    public Tag() {}
    public Tag(int id, String name, int uploadedByUserId) {
        this.id = id;
        this.name = name;
        this.uploadedByUserId = uploadedByUserId;
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
    public int getUploadedByUserId() {
        return uploadedByUserId;
    }
    public void setUploadedByUserId(int uploadedByUserId) {
        this.uploadedByUserId = uploadedByUserId;
    }
}
