package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.model.Link;

import java.util.List;
import java.util.Map;

public interface LinkDao {
    List<Link> getLinks();
    Link getLinkById(int linkId);
    Map<Integer, List<Link>> getLinksByTrackIds(List<Integer> trackIds);
    Link createLink(Link newLink);
    Link updateLink(Link link);
    int deleteLinkById(int linkId);
}
