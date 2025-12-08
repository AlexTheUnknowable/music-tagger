package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.model.Track;

import java.util.List;

public interface TrackDao {
    List<Track> getTracks();
    Track getTrackById(int trackId);
    List<Track> getTracksByName(String name);
    List<Track> getTracksByArtist(int artistId);
    List<Track> getTracksBySource(int sourceId);
    List<Track> getTracksByGlobalTags(List<Integer> tagIds);
    List<Track> getTracksByUserTags(int userId, List<Integer> tagIds);
    Track createTrack(Track newTrack);
    Track updateTrack(Track track);
    int deleteTrackById(int trackId);
}
