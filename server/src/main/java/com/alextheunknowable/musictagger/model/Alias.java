package com.alextheunknowable.musictagger.model;

public class Alias {
    private int id;
    private int trackId;
    private String alias;
    private int uploaderId;

    public Alias() {}
    public Alias(int id, int trackId, String alias, int uploaderId) {
        this.id = id;
        this.trackId = trackId;
        this.alias = alias;
        this.uploaderId = uploaderId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTrackId() {
        return trackId;
    }
    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public int getUploaderId() {
        return uploaderId;
    }
    public void setUploaderId(int uploaderId) {
        this.uploaderId = uploaderId;
    }
}
