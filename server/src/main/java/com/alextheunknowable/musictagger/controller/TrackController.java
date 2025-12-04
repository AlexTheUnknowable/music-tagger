package com.alextheunknowable.musictagger.controller;

import com.alextheunknowable.musictagger.model.Track;
import com.alextheunknowable.musictagger.service.TrackService;
import jakarta.validation.Valid;
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
                            @RequestParam(required = false) Object tags,
                            @RequestParam(required = false) Object yourTags) {
        return trackService.getTracks(name, artistId, sourceId, tags, yourTags);
    }

    @GetMapping("/{id}")
    public Track get(@PathVariable int id) {
        return trackService.getTrackById(id);
    }

    @PostMapping
    public Track add(@Valid @RequestBody Track track) {
        return trackService.createTrack(track);
    }

    @PutMapping("/{id}")
    public Track update(@PathVariable int id, @RequestBody Track track, Principal principal) {
        return trackService.updateTrack(id, track, principal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        trackService.deleteTrack(id);
    }
}
