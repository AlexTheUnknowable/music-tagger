package com.alextheunknowable.musictagger.model;

public class Track {
    private int id;
    private int sourceId;
    private String name;
    private int uploadedByUserId;

    public Track() {}
    public Track(int id, int sourceId, String name, int uploadedByUserId) {
        this.id = id;
        this.sourceId = sourceId;
        this.name = name;
        this.uploadedByUserId = uploadedByUserId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSourceId() {
        return sourceId;
    }
    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
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
