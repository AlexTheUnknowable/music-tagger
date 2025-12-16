package com.alextheunknowable.musictagger.model;

public class Link {
    private int id;
    private OriginType originType;
    private int originId;
    private String url;
    private int uploaderId;

    public Link() {}
    public Link(int id, OriginType originType, int originId, String url, int uploaderId) {
        this.id = id;
        this.originType = originType;
        this.originId = originId;
        this.url = url;
        this.uploaderId = uploaderId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public OriginType getOriginType() {
        return originType;
    }
    public void setOriginType(OriginType originType) {
        this.originType = originType;
    }
    public int getOriginId() {
        return originId;
    }
    public void setOriginId(int originId) {
        this.originId = originId;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getUploaderId() {
        return uploaderId;
    }
    public void setUploaderId(int uploaderId) {
        this.uploaderId = uploaderId;
    }
}
