package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.model.Artist;

import java.util.List;
import java.util.Map;

public interface ArtistDao {
    List<Artist> getArtists();
    Artist getArtistById(int artistId);
    List<Artist> getArtistsByName(String name);
    Map<Integer, List<Artist>> getArtistsByTrackIds(List<Integer> trackIds);
    Artist createArtist(Artist newArtist);
    Artist updateArtist(Artist artist);
    int deleteArtistById(int artistId);
}
