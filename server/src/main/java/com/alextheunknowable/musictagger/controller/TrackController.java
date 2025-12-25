package com.alextheunknowable.musictagger.controller;

import com.alextheunknowable.musictagger.model.Track;
import com.alextheunknowable.musictagger.model.TrackCreationDto;
import com.alextheunknowable.musictagger.model.TrackDto;
import com.alextheunknowable.musictagger.model.TrackSearchCriteria;
import com.alextheunknowable.musictagger.service.TrackService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tracks")
public class TrackController {
    private final TrackService trackService;
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public List<TrackDto> list(@ModelAttribute TrackSearchCriteria tsc, Principal principal) {
        return trackService.getTracks(tsc, principal);
        // example query: /tracks?name=Imagine&artistId=3&globalTagIds=1&globalTagIds=2&userTagIds=5
    }

    @GetMapping("/{id}")
    public Track get(@PathVariable int id) {
        return trackService.getTrackById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public TrackDto add(@Valid @RequestBody TrackCreationDto trackCDto, Principal principal) {
        return trackService.createTrack(trackCDto, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public Track update(@PathVariable int id, @RequestBody Track track, Principal principal) {
        return trackService.updateTrack(id, track, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, Principal principal) {
        trackService.deleteTrack(id, principal);
    }
}
