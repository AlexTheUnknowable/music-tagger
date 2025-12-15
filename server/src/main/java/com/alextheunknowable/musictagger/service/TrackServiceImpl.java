package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.LinkDao;
import com.alextheunknowable.musictagger.dao.TrackArtistDao;
import com.alextheunknowable.musictagger.dao.TrackDao;
import com.alextheunknowable.musictagger.dao.UserDao;
import com.alextheunknowable.musictagger.exception.InsufficientPermissionsException;
import com.alextheunknowable.musictagger.exception.NotFoundException;
import com.alextheunknowable.musictagger.model.*;
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
    private final LinkDao linkDao;
    private final TrackArtistDao trackArtistDao;
    private final ArtistService artistService;
    public TrackServiceImpl(TrackDao trackDao, UserDao userDao, LinkDao linkDao,
                            TrackArtistDao trackArtistDao, ArtistService artistService) {
        this.trackDao = trackDao;
        this.userDao = userDao;
        this.linkDao = linkDao;
        this.trackArtistDao = trackArtistDao;
        this.artistService = artistService;
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
            int userId = getUserIdFromPrincipal(principal);
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
    public TrackDto createTrack(TrackCreationDto dto, Principal principal) {
        // validation
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Track must have a name");
        }
        if (dto.getLinkUrls() == null || dto.getLinkUrls().isEmpty()) {
            throw new IllegalArgumentException("Track must have at least one link");
        }
        TrackDto newDto = new TrackDto();
        int userId = getUserIdFromPrincipal(principal);

        // name + optional source
        Track track = new Track();
        track.setName(dto.getName());
        track.setSourceId(dto.getSourceId());
        track.setUploadedByUserId(userId);
        Track createdTrack = trackDao.createTrack(track);

        newDto.setId(createdTrack.getId());
        newDto.setName(createdTrack.getName());
        newDto.setSourceId(createdTrack.getSourceId());
        newDto.setUploadedByUserId(createdTrack.getUploadedByUserId());

        // link(s)
        List<String> linkUrls = dto.getLinkUrls();
        List<Integer> createdLinkIds = new ArrayList<>();
        for (String url : linkUrls) {
            Link link = new Link();
            link.setOriginType(OriginType.TRACK);
            link.setOriginId(createdTrack.getId());
            link.setUrl(url);
            link.setUploadedByUserId(userId);
            Link createdLink = linkDao.createLink(link);
            createdLinkIds.add(createdLink.getId());
        }
        newDto.setLinkIds(createdLinkIds);
        //TODO: change from using link dao to link service

        // optional artist(s)
        List<Integer> artistIds = new ArrayList<>();
        if (dto.getExistingArtistIds() != null && !dto.getExistingArtistIds().isEmpty()) {
            artistIds.addAll(dto.getExistingArtistIds());
        }
        if (dto.getNewArtistNames() != null && !dto.getNewArtistNames().isEmpty()) {
            // create new artist(s) and add their ids to list
            for (String artistName : dto.getNewArtistNames()) {
                Artist createdArtist = artistService.createArtist(artistName, userId);
                artistIds.add(createdArtist.getId());
            }
        }
        if (!artistIds.isEmpty()) {
            for (Integer artistId : artistIds) {
                trackArtistDao.addArtistToTrack(createdTrack.getId(), artistId);
            }
            newDto.setArtistIds(artistIds);
        }

        //maybe add these in future
        //newDto.setTagIds();
        //newDto.setAliases();
        //newDto.setImageUrl();
        return newDto;
    }

    @Override
    public Track updateTrack(int id, Track updatedTrack, Principal principal) {
        //TODO: change from using user dao to user service
        User user = userDao.getUserByUsername(principal.getName());

        Track existingTrack = trackDao.getTrackById(id);

        if (existingTrack == null) throw new NotFoundException("Track not found.");
        if (!userUploadedTrack(user, existingTrack) && !isUserAdmin(user)) {
            throw new InsufficientPermissionsException("User did not upload this track, and cannot update it.");
        }

        updatedTrack.setId(id);
        updatedTrack.setUploadedByUserId(existingTrack.getUploadedByUserId());
        return trackDao.updateTrack(updatedTrack);
    }

    @Override
    public void deleteTrack(int id, Principal principal) {
        //TODO: change from using user dao to user service
        User user = userDao.getUserByUsername(principal.getName());

        Track track = trackDao.getTrackById(id);

        if (track == null) throw new NotFoundException("Track with id " + id + " not found.");
        if (!userUploadedTrack(user, track) && !isUserAdmin(user)) {
            throw new InsufficientPermissionsException("User did not upload this track, and cannot delete it.");
        }

        int numberOfRowsDeleted = trackDao.deleteTrackById(id);

        if (numberOfRowsDeleted == 0) {
            throw new NotFoundException("Track not found: " + id);
        }
    }

    private List<Track> intersect(List<Track> a, List<Track> b) {
        if (a.isEmpty() || b.isEmpty()) return List.of();
        Set<Integer> ids = b.stream().map(Track::getId).collect(Collectors.toSet()); // Put b into a set of IDs for fast lookup
        return a.stream()
                .filter(track -> ids.contains(track.getId()))
                .toList();
    }

    private int getUserIdFromPrincipal(Principal principal) {
        //TODO: change from using user dao to user service
        return userDao.getUserByUsername(principal.getName()).getId();
    }

    private boolean isUserAdmin(User user) {
        return user.getRole().equals("ROLE_ADMIN");
    }

    private boolean userUploadedTrack(User user, Track track) {
        return track.getUploadedByUserId() == user.getId();
    }
}
