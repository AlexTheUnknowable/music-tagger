package com.alextheunknowable.musictagger.model;

import java.util.ArrayList;
import java.util.List;

public class TrackDto{
    private int id;
    private String name;
    private Integer sourceId;
    private String sourceName;
    private int uploaderId;
    private List<Artist> artists = new ArrayList<>();
    private List<Link> links = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private List<Alias> aliases = new ArrayList<>();
    private String imageUrl;

    public TrackDto() {}

    public TrackDto(Track t) {
        this.id = t.getId();
        this.sourceId = t.getSourceId();
        this.name = t.getName();
        this.uploaderId = t.getUploaderId();
    }

    public TrackDto(int id, String name, Integer sourceId, String sourceName, int uploaderId,
                    List<Artist> artists, List<Link> links, List<Tag> tags, List<Alias> aliases, String imageUrl) {
        this.id = id;
        this.sourceId = sourceId;
        this.sourceName = sourceName;
        this.name = name;
        this.uploaderId = uploaderId;
        this.artists = artists != null ? new ArrayList<>(artists) : new ArrayList<>();
        this.links = links != null ? new ArrayList<>(links) : new ArrayList<>();
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
        this.aliases = aliases != null ? new ArrayList<>(aliases) : new ArrayList<>();
        this.imageUrl = imageUrl;
    }

    public static TrackDto from(
            TrackCoreDto track,
            List<Artist> artists,
            List<Link> links,
            List<Tag> tags,
            List<Alias> aliases) {
        TrackDto dto = new TrackDto();

        dto.setId(track.getId());
        dto.setName(track.getName());
        dto.setUploaderId(track.getUploaderId());
        dto.setSourceId(track.getSourceId());
        dto.setSourceName(track.getSourceName());
        dto.setImageUrl(track.getImageUrl());

        dto.setArtists(artists != null ? artists : List.of());
        dto.setLinks(links != null ? links : List.of());
        dto.setTags(tags != null ? tags : List.of());
        dto.setAliases(aliases != null ? aliases : List.of());

        return dto;
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
    public List<Artist> getArtists() {
        return artists;
    }
    public void setArtists(List<Artist> artists) {
        this.artists = artists != null ? new ArrayList<>(artists) : new ArrayList<>();
    }
    public List<Link> getLinks() {
        return links;
    }
    public void setLinks(List<Link> links) {
        this.links = links != null ? new ArrayList<>(links) : new ArrayList<>();
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
    }
    public List<Alias> getAliases() {
        return aliases;
    }
    public void setAliases(List<Alias> aliases) {
        this.aliases = aliases != null ? new ArrayList<>(aliases) : new ArrayList<>();
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}