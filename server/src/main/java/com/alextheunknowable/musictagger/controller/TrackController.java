package com.alextheunknowable.musictagger.controller;

import com.alextheunknowable.musictagger.model.Track;
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
    public List<Track> list(@RequestParam(required = false) String name,
                            @RequestParam(required = false) Integer artistId,
                            @RequestParam(required = false) Integer sourceId,
                            @RequestParam(required = false, defaultValue = "") List<Integer> globalTagIds,
                            @RequestParam(required = false, defaultValue = "") List<Integer> userTagIds,
                            Principal principal) {

        return trackService.getTracks(name, artistId, sourceId, globalTagIds, userTagIds, principal);
    }

    @GetMapping("/{id}")
    public Track get(@PathVariable int id) {
        return trackService.getTrackById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public Track add(@Valid @RequestBody Track track) {
        return trackService.createTrack(track);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public Track update(@PathVariable int id, @RequestBody Track track, Principal principal) {
        return trackService.updateTrack(id, track, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        trackService.deleteTrack(id);
    }
}
