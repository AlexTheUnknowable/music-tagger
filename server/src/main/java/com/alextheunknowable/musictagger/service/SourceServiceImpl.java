package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.SourceDao;
import com.alextheunknowable.musictagger.exception.NotFoundException;
import com.alextheunknowable.musictagger.model.Source;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class SourceServiceImpl implements SourceService{
    private final SourceDao sourceDao;
    private final AuthService authService;
    public SourceServiceImpl(SourceDao sourceDao, AuthService authService) {
        this.sourceDao = sourceDao;
        this.authService = authService;
    }

    @Override
    public List<Source> getSources(String name) {
        if (name != null && !name.isBlank()) return sourceDao.getSourcesByName(name);
        return sourceDao.getSources();
    }

    @Override
    public Source getSourceById(int id) {
        return sourceDao.getSourceById(id);
    }

    @Override
    public Source createSource(Source source, Principal principal) {
        if (source.getName() == null || source.getName().isBlank()) {
            throw new IllegalArgumentException("Source must have a name");
        }
        source.setUploaderId(authService.getUserIdFromPrincipal(principal));
        return sourceDao.createSource(source);
    }

    @Override
    public Source updateSource(int id, Source updatedSource, Principal principal) {
        Source existingSource = sourceDao.getSourceById(id);

        if (existingSource == null) throw new NotFoundException("Source not found.");
        authService.assertUserCanModify(principal, existingSource.getUploaderId(),
                "User does not have permissions to modify this source.");

        updatedSource.setId(id);
        updatedSource.setUploaderId(existingSource.getUploaderId());
        return sourceDao.updateSource(updatedSource);
    }

    @Override
    public void deleteSource(int id, Principal principal) {
        Source source = sourceDao.getSourceById(id);

        if (source == null) throw new NotFoundException("Source with id " + id + " not found.");
        authService.assertUserCanModify(principal, source.getUploaderId(),
                "User does not have permissions to delete this source.");

        int numberOfRowsDeleted = sourceDao.deleteSourceById(id);

        if (numberOfRowsDeleted == 0) throw new NotFoundException("Source with id " + id + " not found.");
    }

}
