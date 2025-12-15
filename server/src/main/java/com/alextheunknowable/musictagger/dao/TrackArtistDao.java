package com.alextheunknowable.musictagger.dao;

import java.util.List;

public interface TrackArtistDao {
    void addArtistToTrack(int trackId, int artistId);
    int removeArtistFromTrack(int trackId, int artistId);
    List<Integer> getArtistIdsForTrack(int trackId);
}
