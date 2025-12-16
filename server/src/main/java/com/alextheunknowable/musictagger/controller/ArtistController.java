package com.alextheunknowable.musictagger.controller;

import com.alextheunknowable.musictagger.model.Artist;
import com.alextheunknowable.musictagger.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public List<Artist> list(@RequestParam(required = false) String name) {
        return artistService.getArtists(name);
    }

    @GetMapping("/{id}")
    public Artist get(@PathVariable int id) {
        return artistService.getArtistById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public Artist add(@Valid @RequestBody Artist artist, Principal principal) {
        return artistService.createArtist(artist, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public Artist update(@PathVariable int id, @RequestBody Artist artist, Principal principal) {
        return artistService.updateArtist(id, artist, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, Principal principal) {
        artistService.deleteArtist(id, principal);
    }
}
