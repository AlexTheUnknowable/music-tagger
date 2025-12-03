package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.model.Link;

import java.util.List;

public interface LinkDao {
    List<Link> getLinks();
    Link getLinkById(int linkId);
    Link createLink(Link newLink);
    Link updateLink(Link link);
    int deleteLinkById(int linkId);
}
