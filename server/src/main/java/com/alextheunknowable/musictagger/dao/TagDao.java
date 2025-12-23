package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.model.Tag;

import java.util.List;
import java.util.Map;

public interface TagDao {
    List<Tag> getTags();
    Tag getTagById(int tagId);
    List<Tag> getTagsByName(String name);
    Map<Integer, List<Tag>> getTagsByTrackIds(List<Integer> trackIds);
    Tag createTag(Tag newTag);
    Tag updateTag(Tag Tag);
    int deleteTagById(int tagId);
}
