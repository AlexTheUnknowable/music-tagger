package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.Tag;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface TagService {
    List<Tag> getTags(String name);
    Tag getTagById(int id);
    Map<Integer, List<Tag>> getTagsByTrackIds(List<Integer> trackIds);
    Tag createTag(Tag tag, Principal principal);
    Tag updateTag(int id, Tag tag, Principal principal);
    void deleteTag(int id, Principal principal);
}
