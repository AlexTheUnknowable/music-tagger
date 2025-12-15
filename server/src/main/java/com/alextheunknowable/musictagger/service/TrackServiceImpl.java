package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.TrackDao;
import com.alextheunknowable.musictagger.dao.UserDao;
import com.alextheunknowable.musictagger.model.Track;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService{
    private final TrackDao trackDao;
    private final UserDao userDao;
    public TrackServiceImpl(TrackDao trackDao, UserDao userDao) {
        this.trackDao = trackDao;
        this.userDao = userDao;
    }

    @Override
    public List<Track> getTracks(String name,
                                 Integer artistId,
                                 Integer sourceId,
                                 List<Integer> globalTagIds,
                                 List<Integer> userTagIds,
                                 Principal principal) {

        // artist/source filters
        List<Track> tracks;
        if (artistId != null) {
            tracks = trackDao.getTracksByArtist(artistId);
        } else if (sourceId != null) {
            tracks = trackDao.getTracksBySource(sourceId);
        } else {
            tracks = trackDao.getTracks();
        }

        // tag filters
        if (globalTagIds != null && !globalTagIds.isEmpty()) {
            List<Track> globalTaggedTracks = trackDao.getTracksByGlobalTags(globalTagIds);
            tracks = intersect(tracks, globalTaggedTracks);
        }
        if (userTagIds != null && !userTagIds.isEmpty()) {
            int userId = userDao.getUserByUsername(principal.getName()).getId();
            List<Track> userTaggedTracks = trackDao.getTracksByUserTags(userId, userTagIds);
            tracks = intersect(tracks, userTaggedTracks);
        }

        // name filter
        if (name != null && !name.isBlank()) {
            tracks = tracks.stream()
                    .filter(track -> track.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        return tracks;
    }

    @Override
    public Track getTrackById(int id) {
        return trackDao.getTrackById(id);
    }

    @Override
    public Track createTrack(Track track) {
        return trackDao.createTrack(track);
        // requires name and 1 link, optionally artist, source, and/or more links
    }

    @Override
    public Track updateTrack(int id, Track track, Principal principal) {
        track.setId(id);
        return trackDao.updateTrack(track);
        //requires user to be admin or uploader
    }

    @Override
    public void deleteTrack(int id) {
        int numberOfRowsDeleted = trackDao.deleteTrackById(id);
        //requires user to be admin or uploader
    }

    private List<Track> intersect(List<Track> a, List<Track> b) {
        if (a.isEmpty() || b.isEmpty()) return List.of();
        Set<Integer> ids = b.stream().map(Track::getId).collect(Collectors.toSet()); // Put b into a set of IDs for fast lookup
        return a.stream()
                .filter(track -> ids.contains(track.getId()))
                .toList();
    }
}
