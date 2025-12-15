package com.alextheunknowable.musictagger.model;

import java.util.ArrayList;
import java.util.List;

public class TrackDto{
    private int id;
    private String name;
    private Integer sourceId;
    private int uploadedByUserId;
    private List<Integer> artistIds = new ArrayList<>();
    private List<Integer> linkIds = new ArrayList<>();
    private List<Integer> tagIds = new ArrayList<>();
    private List<String> aliases = new ArrayList<>();
    private String imageUrl;

    public TrackDto() {}

    public TrackDto(Track t) {
        this.id = t.getId();
        this.sourceId = t.getSourceId();
        this.name = t.getName();
        this.uploadedByUserId = t.getUploadedByUserId();
    }

    public TrackDto(int id, Integer sourceId, String name, int uploadedByUserId, List<Integer> artistIds,
                    List<Integer> linkIds, List<Integer> tagIds, List<String> aliases, String imageUrl) {
        this.id = id;
        this.sourceId = sourceId;
        this.name = name;
        this.uploadedByUserId = uploadedByUserId;
        this.artistIds = artistIds != null ? new ArrayList<>(artistIds) : new ArrayList<>();
        this.linkIds = linkIds != null ? new ArrayList<>(linkIds) : new ArrayList<>();
        this.tagIds = tagIds != null ? new ArrayList<>(tagIds) : new ArrayList<>();
        this.aliases = aliases != null ? new ArrayList<>(aliases) : new ArrayList<>();
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
    public List<Integer> getArtistIds() {
        return artistIds;
    }
    public void setArtistIds(List<Integer> artistIds) {
        this.artistIds = artistIds != null ? new ArrayList<>(artistIds) : new ArrayList<>();
    }
    public List<Integer> getLinkIds() {
        return linkIds;
    }
    public void setLinkIds(List<Integer> linkIds) {
        this.linkIds = linkIds != null ? new ArrayList<>(linkIds) : new ArrayList<>();
    }
    public List<Integer> getTagIds() {
        return tagIds;
    }
    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds != null ? new ArrayList<>(tagIds) : new ArrayList<>();
    }
    public List<String> getAliases() {
        return aliases;
    }
    public void setAliases(List<String> aliases) {
        this.aliases = aliases != null ? new ArrayList<>(aliases) : new ArrayList<>();
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}