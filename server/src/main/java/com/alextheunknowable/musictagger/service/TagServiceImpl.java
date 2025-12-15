package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.TagDao;
import com.alextheunknowable.musictagger.model.Tag;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{
    private final TagDao tagDao;
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> getTags(String name) {

        List<Tag> tags = tagDao.getTags();

        // name filter
        if (name != null && !name.isBlank()) {
            tags = tags.stream()
                    .filter(tag -> tag.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        return tags;
    }

    @Override
    public Tag getTagById(int id) {
        return tagDao.getTagById(id);
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagDao.createTag(tag);
        // requires name and 1 link
    }

    @Override
    public Tag updateTag(int id, Tag tag, Principal principal) {
        tag.setId(id);
        return tagDao.updateTag(tag);
        //requires user to be admin or uploader
    }

    @Override
    public void deleteTag(int id) {
        int numberOfRowsDeleted = tagDao.deleteTagById(id);
        //requires user to be admin or uploader
    }

}
