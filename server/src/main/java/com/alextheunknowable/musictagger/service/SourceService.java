package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.Source;

import java.security.Principal;
import java.util.List;

public interface SourceService {
    List<Source> getSources(String name);
    Source getSourceById(int id);
    Source createSource(Source source, Principal principal);
    Source updateSource(int id, Source source, Principal principal);
    void deleteSource(int id, Principal principal);
}
