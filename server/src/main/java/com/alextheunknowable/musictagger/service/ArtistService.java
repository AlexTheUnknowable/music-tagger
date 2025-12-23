package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.Artist;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface ArtistService {
    List<Artist> getArtists(String name);
    Artist getArtistById(int id);
    Map<Integer, List<Artist>> getArtistsByTrackIds(List<Integer> trackIds);
    Artist createArtist(Artist artist, Principal principal);
    Artist createArtist(String name, int userId);
    Artist updateArtist(int id, Artist artist, Principal principal);
    void deleteArtist(int id, Principal principal);
}
