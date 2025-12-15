package com.alextheunknowable.musictagger.controller;

import com.alextheunknowable.musictagger.model.Link;
import com.alextheunknowable.musictagger.service.LinkService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/links")
public class LinkController {
    private final LinkService linkService;
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping
    public List<Link> list() {
        return linkService.getLinks();
    }

    @GetMapping("/{id}")
    public Link get(@PathVariable int id) {
        return linkService.getLinkById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public Link add(@Valid @RequestBody Link link) {
        return linkService.createLink(link);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public Link update(@PathVariable int id, @RequestBody Link link, Principal principal) {
        return linkService.updateLink(id, link, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        linkService.deleteLink(id);
    }
}
