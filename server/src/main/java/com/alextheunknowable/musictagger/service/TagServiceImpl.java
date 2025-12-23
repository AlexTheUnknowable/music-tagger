package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.TagDao;
import com.alextheunknowable.musictagger.exception.NotFoundException;
import com.alextheunknowable.musictagger.model.Tag;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService{
    private final TagDao tagDao;
    private final AuthService authService;
    public TagServiceImpl(TagDao tagDao, AuthService authService) {
        this.tagDao = tagDao;
        this.authService = authService;
    }

    @Override
    public List<Tag> getTags(String name) {
        if (name != null && !name.isBlank()) return tagDao.getTagsByName(name);
        return tagDao.getTags();
    }

    @Override
    public Tag getTagById(int id) {
        return tagDao.getTagById(id);
    }

    @Override
    public Map<Integer, List<Tag>> getTagsByTrackIds(List<Integer> trackIds) {
        return tagDao.getTagsByTrackIds(trackIds);
    }

    @Override
    public Tag createTag(Tag tag, Principal principal) {
        if (tag.getName() == null || tag.getName().isBlank()) {
            throw new IllegalArgumentException("Tag must have a name");
        }
        tag.setUploaderId(authService.getUserIdFromPrincipal(principal));
        return tagDao.createTag(tag);
    }

    @Override
    public Tag updateTag(int id, Tag updatedTag, Principal principal) {
        Tag existingTag = tagDao.getTagById(id);

        if (existingTag == null) throw new NotFoundException("Tag not found.");
        authService.assertUserCanModify(principal, existingTag.getUploaderId(),
                "User does not have permissions to modify this tag.");

        updatedTag.setId(id);
        updatedTag.setUploaderId(existingTag.getUploaderId());
        return tagDao.updateTag(updatedTag);
    }

    @Override
    public void deleteTag(int id, Principal principal) {
        Tag tag = tagDao.getTagById(id);

        if (tag == null) throw new NotFoundException("Tag with id " + id + " not found.");
        authService.assertUserCanModify(principal, tag.getUploaderId(),
                "User does not have permissions to delete this tag.");

        int numberOfRowsDeleted = tagDao.deleteTagById(id);

        if (numberOfRowsDeleted == 0) throw new NotFoundException("Tag with id " + id + " not found.");
    }

}
