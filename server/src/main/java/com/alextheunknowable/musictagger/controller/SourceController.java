package com.alextheunknowable.musictagger.controller;

import com.alextheunknowable.musictagger.model.Source;
import com.alextheunknowable.musictagger.service.SourceService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sources")
public class SourceController {
    private final SourceService sourceService;
    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping
    public List<Source> list(@RequestParam(required = false) String name) {
        return sourceService.getSources(name);
    }

    @GetMapping("/{id}")
    public Source get(@PathVariable int id) {
        return sourceService.getSourceById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public Source add(@Valid @RequestBody Source source, Principal principal) {
        return sourceService.createSource(source, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public Source update(@PathVariable int id, @RequestBody Source source, Principal principal) {
        return sourceService.updateSource(id, source, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, Principal principal) {
        sourceService.deleteSource(id, principal);
    }
}
