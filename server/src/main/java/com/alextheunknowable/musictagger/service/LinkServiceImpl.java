package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.LinkDao;
import com.alextheunknowable.musictagger.exception.NotFoundException;
import com.alextheunknowable.musictagger.model.Artist;
import com.alextheunknowable.musictagger.model.Link;
import com.alextheunknowable.musictagger.model.OriginType;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class LinkServiceImpl implements LinkService{
    private final LinkDao linkDao;
    private final AuthService authService;
    public LinkServiceImpl(LinkDao linkDao, AuthService authService) {
        this.linkDao = linkDao;
        this.authService = authService;
    }

    @Override
    public List<Link> getLinks() {
        return linkDao.getLinks();
    }

    @Override
    public Link getLinkById(int id) {
        return linkDao.getLinkById(id);
    }

    @Override
    public Link createLink(Link link, Principal principal) {
        link.setUploaderId(authService.getUserIdFromPrincipal(principal));
        return linkDao.createLink(link);
    }

    @Override
    public Link createLink(OriginType originType, int originId, String url, int userId) {
        //note: make this transactional if i ever add side effects
        if (originType == null) {
            throw new IllegalArgumentException("An unexpected error occurred: OriginType is null");
        }
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("Link must have a url");
        }
        Link newLink = new Link();
        newLink.setOriginType(originType);
        newLink.setOriginId(originId);
        newLink.setUrl(url);
        newLink.setUploaderId(userId);
        return linkDao.createLink(newLink);
    }

    @Override
    public Link updateLink(int id, Link updatedLink, Principal principal) {
        Link existingLink = linkDao.getLinkById(id);

        if (existingLink == null) throw new NotFoundException("Link not found.");
        authService.assertUserCanModify(principal, existingLink.getUploaderId(),
                "User does not have permissions to modify this link.");

        updatedLink.setId(id);
        updatedLink.setUploaderId(existingLink.getUploaderId());
        return linkDao.updateLink(updatedLink);
    }

    @Override
    public void deleteLink(int id, Principal principal) {
        Link link = linkDao.getLinkById(id);

        if (link == null) throw new NotFoundException("Link with id " + id + " not found.");
        authService.assertUserCanModify(principal, link.getUploaderId(),
                "User does not have permissions to delete this link.");

        int numberOfRowsDeleted = linkDao.deleteLinkById(id);

        if (numberOfRowsDeleted == 0) throw new NotFoundException("Link with id " + id + " not found.");
    }

}
