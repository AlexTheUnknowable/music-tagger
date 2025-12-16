package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.Link;
import com.alextheunknowable.musictagger.model.OriginType;

import java.security.Principal;
import java.util.List;

public interface LinkService {
    List<Link> getLinks();
    Link getLinkById(int id);
    Link createLink(Link link, Principal principal);
    Link createLink(OriginType originType, int originId, String url, int userId);
    Link updateLink(int id, Link link, Principal principal);
    void deleteLink(int id, Principal principal);
}
