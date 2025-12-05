package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.Track;

import java.security.Principal;
import java.util.List;

public interface TrackService {
    List<Track> getTracks(String name, Integer artistId, Integer sourceId, List<Integer> globalTagIds, List<Integer> userTagIds);
    Track getTrackById(int id);
    Track createTrack(Track track);
    Track updateTrack(int id, Track track, Principal principal);
    void deleteTrack(int id);
}
