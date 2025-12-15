package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.Link;

import java.security.Principal;
import java.util.List;

public interface LinkService {
    List<Link> getLinks();
    Link getLinkById(int id);
    Link createLink(Link link);
    Link updateLink(int id, Link link, Principal principal);
    void deleteLink(int id);
}
