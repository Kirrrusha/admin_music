package org.mymusic.core.endpoint.api;

import lombok.RequiredArgsConstructor;
import org.mymusic.core.domain.Track;
import org.mymusic.core.service.TracksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor
public class TracksEndpoint {

    private final TracksService service;

    @GetMapping("/my")
    public List<Track> getUserTracks(@RequestParam Integer limit, @RequestParam Integer offset) {
        return service.getUserTracks(limit, offset);
    }

    @GetMapping
    public List<Track> getTracks(@RequestParam Integer limit, @RequestParam Integer offset) {
        return service.getTracks(limit, offset);
    }
}
