package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.Track;
import com.alextheunknowable.musictagger.model.TrackCreationDto;
import com.alextheunknowable.musictagger.model.TrackDto;

import java.security.Principal;
import java.util.List;

public interface TrackService {
    List<Track> getTracks(String name,
                          Integer artistId,
                          Integer sourceId,
                          List<Integer> globalTagIds,
                          List<Integer> userTagIds,
                          Principal principal);
    Track getTrackById(int id);
    TrackDto createTrack(TrackCreationDto trackCDto, Principal principal);
    Track updateTrack(int id, Track track, Principal principal);
    void deleteTrack(int id, Principal principal);
}
