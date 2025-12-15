package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.LinkDao;
import com.alextheunknowable.musictagger.model.Link;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class LinkServiceImpl implements LinkService{
    private final LinkDao linkDao;
    public LinkServiceImpl(LinkDao linkDao) {
        this.linkDao = linkDao;
    }

    @Override
    public List<Link> getLinks() {
        List<Link> links = linkDao.getLinks();
        return links;
    }

    @Override
    public Link getLinkById(int id) {
        return linkDao.getLinkById(id);
    }

    @Override
    public Link createLink(Link link) {
        return linkDao.createLink(link);
        // requires name and 1 link
    }

    @Override
    public Link updateLink(int id, Link link, Principal principal) {
        link.setId(id);
        return linkDao.updateLink(link);
        //requires user to be admin or uploader
    }

    @Override
    public void deleteLink(int id) {
        int numberOfRowsDeleted = linkDao.deleteLinkById(id);
        //requires user to be admin or uploader
    }

}
