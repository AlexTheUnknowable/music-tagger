package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.Track;

import java.security.Principal;
import java.util.List;

public interface TrackService {
    List<Track> getTracks(String name, Integer artistId, Integer sourceId, Object tags, Object yourTags);
    Track getTrackById(int id);
    Track createTrack(Track track);
    Track updateTrack(int id, Track track, Principal principal);
    void deleteTrack(int id);
}
