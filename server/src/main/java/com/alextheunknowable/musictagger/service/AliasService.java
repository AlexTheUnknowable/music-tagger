package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.Alias;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface AliasService {
    List<Alias> getAliases();
    Alias getAliasById(int id);
    Map<Integer, List<Alias>> getAliasesByTrackIds(List<Integer> trackIds);
    Alias createAlias(Alias alias, Principal principal);
    Alias updateAlias(int id, Alias alias, Principal principal);
    void deleteAlias(int id, Principal principal);
}
