package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.model.Tag;

import java.util.List;

public interface TagDao {
    List<Tag> getTags();
    Tag getTagById(int tagId);
    Tag getTagByName(String name);
    Tag createTag(Tag newTag);
    Tag updateTag(Tag Tag);
    int deleteTagById(int tagId);
}
