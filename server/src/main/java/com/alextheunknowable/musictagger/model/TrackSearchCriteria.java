package com.alextheunknowable.musictagger.model;

import java.util.List;

public class TrackSearchCriteria {
    private String name;
    private Integer artistId;
    private Integer sourceId;
    private List<Integer> globalTagIds;
    private List<Integer> userTagIds;

    public TrackSearchCriteria() {}
    public TrackSearchCriteria(String name, Integer artistId, Integer sourceId, List<Integer> globalTagIds, List<Integer> userTagIds) {
        this.name = name;
        this.artistId = artistId;
        this.sourceId = sourceId;
        this.globalTagIds = globalTagIds;
        this.userTagIds = userTagIds;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getArtistId() {
        return artistId;
    }
    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
    public Integer getSourceId() {
        return sourceId;
    }
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }
    public List<Integer> getGlobalTagIds() {
        return globalTagIds;
    }
    public void setGlobalTagIds(List<Integer> globalTagIds) {
        this.globalTagIds = globalTagIds;
    }
    public List<Integer> getUserTagIds() {
        return userTagIds;
    }
    public void setUserTagIds(List<Integer> userTagIds) {
        this.userTagIds = userTagIds;
    }
}
