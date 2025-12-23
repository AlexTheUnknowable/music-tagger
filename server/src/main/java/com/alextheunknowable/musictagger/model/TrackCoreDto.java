package com.alextheunknowable.musictagger.model;

import java.util.ArrayList;
import java.util.List;

public class TrackCoreDto{
    private int id;
    private String name;
    private Integer sourceId;
    private String sourceName;
    private int uploaderId;
    private String imageUrl;

    public TrackCoreDto() {}

    public TrackCoreDto(int id, String name, Integer sourceId, String sourceName, int uploaderId, String imageUrl) {
        this.id = id;
        this.sourceId = sourceId;
        this.sourceName = sourceName;
        this.name = name;
        this.uploaderId = uploaderId;
        this.imageUrl = imageUrl;
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
    public String getSourceName() {
        return sourceName;
    }
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
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
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}