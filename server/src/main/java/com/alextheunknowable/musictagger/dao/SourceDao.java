package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.model.Source;

import java.util.List;

public interface SourceDao {
    List<Source> getSources();
    Source getSourceById(int sourceId);
    Source getSourceByName(String name);
    Source createSource(Source newSource);
    Source updateSource(Source source);
    int deleteSourceById(int sourceId);
}
