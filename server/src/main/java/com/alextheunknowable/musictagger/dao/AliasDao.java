package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.model.Alias;

import java.util.List;
import java.util.Map;

public interface AliasDao {
    List<Alias> getAliases();
    Alias getAliasById(int id);
    Map<Integer, List<Alias>> getAliasesByTrackIds(List<Integer> trackIds);
    Alias createAlias(Alias newAlias);
    Alias updateAlias(Alias alias);
    int deleteAliasById(int aliasId);
}
