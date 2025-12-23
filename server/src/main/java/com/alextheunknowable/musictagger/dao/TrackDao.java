package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.model.Track;
import com.alextheunknowable.musictagger.model.TrackCoreDto;
import com.alextheunknowable.musictagger.model.TrackSearchCriteria;

import java.util.List;

public interface TrackDao {
    List<Track> getAllTracks();
    List<TrackCoreDto> getTracksByCriteria(TrackSearchCriteria tsc, Integer userId);
    Track getTrackById(int trackId);
    Track createTrack(Track newTrack);
    Track updateTrack(Track track);
    int deleteTrackById(int trackId);
}
