package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.Tag;

import java.security.Principal;
import java.util.List;

public interface TagService {
    List<Tag> getTags(String name);
    Tag getTagById(int id);
    Tag createTag(Tag tag);
    Tag updateTag(int id, Tag tag, Principal principal);
    void deleteTag(int id);
}
