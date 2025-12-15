package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.Artist;

import java.security.Principal;
import java.util.List;

public interface ArtistService {
    List<Artist> getArtists(String name);
    Artist getArtistById(int id);
    Artist createArtist(Artist artist);
    Artist updateArtist(int id, Artist artist, Principal principal);
    void deleteArtist(int id);
}
