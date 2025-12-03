package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.model.Track;

import java.util.List;

public interface TrackDao {
    List<Track> getTracks();
    Track getTrackById(int trackId);
    Track getTrackByName(String name);
    Track createTrack(Track newTrack);
    Track updateTrack(Track track);
    int deleteTrackById(int trackId);
}
