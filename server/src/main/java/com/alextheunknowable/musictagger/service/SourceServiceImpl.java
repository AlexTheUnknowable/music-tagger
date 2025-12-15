package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.SourceDao;
import com.alextheunknowable.musictagger.model.Source;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class SourceServiceImpl implements SourceService{
    private final SourceDao sourceDao;
    public SourceServiceImpl(SourceDao sourceDao) {
        this.sourceDao = sourceDao;
    }

    @Override
    public List<Source> getSources(String name) {

        List<Source> sources = sourceDao.getSources();

        // name filter
        if (name != null && !name.isBlank()) {
            sources = sources.stream()
                    .filter(source -> source.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        return sources;
    }

    @Override
    public Source getSourceById(int id) {
        return sourceDao.getSourceById(id);
    }

    @Override
    public Source createSource(Source source) {
        return sourceDao.createSource(source);
        // requires name and 1 link
    }

    @Override
    public Source updateSource(int id, Source source, Principal principal) {
        source.setId(id);
        return sourceDao.updateSource(source);
        //requires user to be admin or uploader
    }

    @Override
    public void deleteSource(int id) {
        int numberOfRowsDeleted = sourceDao.deleteSourceById(id);
        //requires user to be admin or uploader
    }

}
