package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.TrackArtistDao;
import com.alextheunknowable.musictagger.dao.TrackDao;
import com.alextheunknowable.musictagger.exception.NotFoundException;
import com.alextheunknowable.musictagger.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackServiceImpl implements TrackService{
    private final TrackDao trackDao;
    private final TrackArtistDao trackArtistDao;
    private final AuthService authService;
    private final LinkService linkService;
    private final ArtistService artistService;
    public TrackServiceImpl(TrackDao trackDao, AuthService authService, LinkService linkService,
                            TrackArtistDao trackArtistDao, ArtistService artistService) {
        this.trackDao = trackDao;
        this.authService = authService;
        this.linkService = linkService;
        this.trackArtistDao = trackArtistDao;
        this.artistService = artistService;
    }

    @Override
    public List<Track> getTracks(TrackSearchCriteria tsc, Principal principal) {
        Integer userId = null;
        if (tsc.getUserTagIds() != null && !tsc.getUserTagIds().isEmpty())
            userId = authService.getUserIdFromPrincipal(principal);
        return trackDao.getTracks(tsc, userId);
    }

    @Override
    public Track getTrackById(int id) {
        return trackDao.getTrackById(id);
    }

    @Override
    @Transactional
    public TrackDto createTrack(TrackCreationDto dto, Principal principal) {
        // validation
        if (dto.getName() == null || dto.getName().isBlank())
            throw new IllegalArgumentException("Track must have a name");
        if (dto.getLinkUrls() == null || dto.getLinkUrls().isEmpty())
            throw new IllegalArgumentException("Track must have at least one link");
        boolean hasSource = dto.getSourceId() != null;
        boolean hasExistingArtists = dto.getExistingArtistIds() != null && !dto.getExistingArtistIds().isEmpty();
        boolean hasNewArtists = dto.getNewArtistNames() != null && !dto.getNewArtistNames().isEmpty();
        if (!hasSource && !hasExistingArtists && !hasNewArtists)
            throw new IllegalArgumentException("Track must have a source or artist");

        // name + source + uploader id
        int userId = authService.getUserIdFromPrincipal(principal);
        Track track = new Track();
        track.setName(dto.getName());
        track.setSourceId(dto.getSourceId());
        track.setUploaderId(userId);
        Track createdTrack = trackDao.createTrack(track);

        // link(s)
        List<String> linkUrls = dto.getLinkUrls();
        List<Integer> createdLinkIds = new ArrayList<>();
        //TODO: put this logic in linkService?
        for (String url : linkUrls) {
            Link link = new Link();
            link.setOriginType(OriginType.TRACK);
            link.setOriginId(createdTrack.getId());
            link.setUrl(url);
            link.setUploaderId(userId);
            Link createdLink = linkService.createLink(link);
            createdLinkIds.add(createdLink.getId());
        }

        // artist(s)
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
        for (Integer artistId : artistIds) {
            trackArtistDao.addArtistToTrack(createdTrack.getId(), artistId);
        }

        return createTrackDto(createdTrack, createdLinkIds, artistIds);
    }

    @Override
    public Track updateTrack(int id, Track updatedTrack, Principal principal) {
        Track existingTrack = trackDao.getTrackById(id);

        if (existingTrack == null) throw new NotFoundException("Track not found.");
        authService.assertUserCanModify(principal, existingTrack.getUploaderId(),
                "User does not have permissions to modify this track.");

        updatedTrack.setId(id);
        updatedTrack.setUploaderId(existingTrack.getUploaderId());
        return trackDao.updateTrack(updatedTrack);
    }

    @Override
    public void deleteTrack(int id, Principal principal) {
        Track track = trackDao.getTrackById(id);

        if (track == null) throw new NotFoundException("Track with id " + id + " not found.");
        authService.assertUserCanModify(principal, track.getUploaderId(),
                "User does not have permissions to delete this track.");

        int numberOfRowsDeleted = trackDao.deleteTrackById(id);

        if (numberOfRowsDeleted == 0) throw new NotFoundException("Track with id " + id + " not found.");
    }

    private TrackDto createTrackDto(Track track, List<Integer> linkIds, List<Integer> artistIds) {
        TrackDto createdTrackDto = new TrackDto();
        createdTrackDto.setId(track.getId());
        createdTrackDto.setName(track.getName());
        createdTrackDto.setSourceId(track.getSourceId());
        createdTrackDto.setUploaderId(track.getUploaderId());
        createdTrackDto.setLinkIds(linkIds);
        createdTrackDto.setArtistIds(artistIds);
        //maybe add these in the future:
        //createdTrackDto.setTagIds();
        //createdTrackDto.setAliases();
        //createdTrackDto.setImageUrl();
        return createdTrackDto;
    }
}
