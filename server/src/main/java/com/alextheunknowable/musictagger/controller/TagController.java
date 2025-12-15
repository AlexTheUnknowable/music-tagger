package com.alextheunknowable.musictagger.controller;

import com.alextheunknowable.musictagger.model.Tag;
import com.alextheunknowable.musictagger.service.TagService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> list(@RequestParam(required = false) String name) {
        return tagService.getTags(name);
    }

    @GetMapping("/{id}")
    public Tag get(@PathVariable int id) {
        return tagService.getTagById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public Tag add(@Valid @RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public Tag update(@PathVariable int id, @RequestBody Tag tag, Principal principal) {
        return tagService.updateTag(id, tag, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        tagService.deleteTag(id);
    }
}
