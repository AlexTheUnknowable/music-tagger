package com.alextheunknowable.musictagger.model;

public class Alias {
    private int id;
    private OriginType originType;
    private int originId;
    private String alias;
    private int uploadedByUserId;

    public Alias() {}
    public Alias(int id, OriginType originType, int originId, String alias, int uploadedByUserId) {
        this.id = id;
        this.originType = originType;
        this.originId = originId;
        this.alias = alias;
        this.uploadedByUserId = uploadedByUserId;
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
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public int getUploadedByUserId() {
        return uploadedByUserId;
    }
    public void setUploadedByUserId(int uploadedByUserId) {
        this.uploadedByUserId = uploadedByUserId;
    }
}
