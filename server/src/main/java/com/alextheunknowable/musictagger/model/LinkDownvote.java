package com.alextheunknowable.musictagger.model;

public class LinkDownvote {
    private int id;
    private int linkId;
    private int userId;

    public LinkDownvote() {}
    public LinkDownvote(int id, int linkId, int userId) {
        this.id = id;
        this.linkId = linkId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getLinkId() {
        return linkId;
    }
    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
