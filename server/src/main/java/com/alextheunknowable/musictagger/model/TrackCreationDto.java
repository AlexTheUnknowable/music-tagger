package com.alextheunknowable.musictagger.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class TrackCreationDto{
    @NotBlank(message = "Track name is required")
    private String name;
    private Integer sourceId;
    private List<Integer> existingArtistIds = new ArrayList<>();
    private List<String> newArtistNames = new ArrayList<>();
    @NotEmpty(message = "At least one link is required")
    private List<@NotBlank String> linkUrls = new ArrayList<>();
    private List<Integer> tagIds = new ArrayList<>();
    private List<String> aliases = new ArrayList<>();

    public TrackCreationDto() {}
    public TrackCreationDto(String name, Integer sourceId, List<Integer> existingArtistIds, List<String> newArtistNames,
                            List<String> linkUrls, List<Integer> tagIds, List<String> aliases) {
        this.name = name;
        this.sourceId = sourceId;
        this.existingArtistIds = existingArtistIds != null ? new ArrayList<>(existingArtistIds) : new ArrayList<>();
        this.newArtistNames = newArtistNames != null ? new ArrayList<>(newArtistNames) : new ArrayList<>();
        this.linkUrls = linkUrls != null ? new ArrayList<>(linkUrls) : new ArrayList<>();
        this.tagIds = tagIds != null ? new ArrayList<>(tagIds) : new ArrayList<>();
        this.aliases = aliases != null ? new ArrayList<>(aliases) : new ArrayList<>();
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
    public List<Integer> getExistingArtistIds() {
        return existingArtistIds;
    }
    public void setExistingArtistIds(List<Integer> existingArtistIds) {
        this.existingArtistIds = existingArtistIds != null ? new ArrayList<>(existingArtistIds) : new ArrayList<>();
    }
    public List<String> getNewArtistNames() {
        return newArtistNames;
    }
    public void setNewArtistNames(List<String> newArtistNames) {
        this.newArtistNames = newArtistNames != null ? new ArrayList<>(newArtistNames) : new ArrayList<>();
    }
    public List<String> getLinkUrls() {
        return linkUrls;
    }
    public void setLinkUrls(List<String> linkUrls) {
        this.linkUrls = linkUrls != null ? new ArrayList<>(linkUrls) : new ArrayList<>();
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
}