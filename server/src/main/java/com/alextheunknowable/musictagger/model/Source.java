package com.alextheunknowable.musictagger.model;

public class Source {
    private int id;
    private String name;
    private int uploaderId;

    public Source() {}
    public Source(int id, String name, int uploaderId) {
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
    public void setUploaderId(int UserId) {
        this.uploaderId = uploaderId;
    }
}
