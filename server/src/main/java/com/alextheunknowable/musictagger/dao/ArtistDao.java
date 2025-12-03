package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.model.Artist;

import java.util.List;

public interface ArtistDao {
    List<Artist> getArtists();
    Artist getArtistById(int artistId);
    Artist getArtistByName(String name);
    Artist createArtist(Artist newArtist);
    Artist updateArtist(Artist artist);
    int deleteArtistById(int artistId);
}
