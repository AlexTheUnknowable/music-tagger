package com.alextheunknowable.musictagger.model;

public class Track {
    private int id;
    private Integer sourceId;
    private String name;
    private int uploaderId;

    public Track() {}
    public Track(int id, Integer sourceId, String name, int uploaderId) {
        this.id = id;
        this.sourceId = sourceId;
        this.name = name;
        this.uploaderId = uploaderId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Integer getSourceId() {
        return sourceId;
    }
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
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
    public void setUploaderId(int uploaderId) {
        this.uploaderId = uploaderId;
    }
}
