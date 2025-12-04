package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.TrackDao;
import com.alextheunknowable.musictagger.model.Track;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class TrackServiceImpl implements TrackService{
    private final TrackDao trackDao;
    public TrackServiceImpl(TrackDao trackDao) {
        this.trackDao = trackDao;
    }

    @Override
    public List<Track> getTracks(String name, Integer artistId, Integer sourceId, Object tags, Object yourTags) {
        List<Track> tracks;
        if (artistId != null || sourceId != null) {
            tracks = trackDao.getTracksByArtistOrSourceOrWhateverMan();
        } else {
            tracks = trackDao.getTracks();
        }
        if (tags == null && yourTags == null) {
            if (name == null) return tracks;
            if (name.isBlank()) return tracks;
        }

        // If we've reached this part of the code, we're filtering by name, tags, or both.
        // could probly be structured better but fine for now lol
        if ((tags != null || yourTags != null) && name != null) { // filtering by tags and names
            return filterByName(filterByTags(tracks, tagsOrYourTagsOrSOmethingIdk), name);
        } else if (tags != null || yourTags != null) { // filtering by tags only
            return filterByTags(tracks, tagsOrYourTagsOrSOmethingIdk);
        } else { // filtering by names only
            return filterByName(tracks, name);
        }
    }

    @Override
    public Track getTrackById(int id) {
        return trackDao.getTrackById(id);
    }

    @Override
    public Track createTrack(Track track) {
        return trackDao.createTrack(track);
    }

    @Override
    public Track updateTrack(int id, Track track, Principal principal) {
        return trackDao.updateTrack(track);
        //requires user to be admin or uploader
    }

    @Override
    public void deleteTrack(int id) {
        int numberOfRowsDeleted = trackDao.deleteTrackById(id);
        //requires user to be admin or uploader
    }

    private List<Track> filterByTags(List<Track> tracks, Object tags) {
        List<Track> matching = new ArrayList<>();
        for (Track track : tracks) {
            if (boolean trackHasTagsOrYOuHaveAssignedThistagIdkManhdvjyeaghfa) {
                matching.add(track);
            }
        }
        return matching;
    }

    private List<Track> filterByName(List<Track> tracks, String name) {
        List<Track> matching = new ArrayList<>();
        for (Track track : tracks) {
            if (track.getName().contains(name)) {
                matching.add(track);
            }
        }
        return matching;
    }
}
