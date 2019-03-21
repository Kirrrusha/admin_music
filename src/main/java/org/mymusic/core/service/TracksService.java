package org.mymusic.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mymusic.core.dao.TracksDao;
import org.mymusic.core.domain.Track;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TracksService {

    private static final Integer MAX_LIMIT = 100;

    private final TracksDao dao;
    private final UsersService usersService;

    public List<Track> getUserTracks(Integer limit, Integer offset) {
        limit = limit > MAX_LIMIT ? MAX_LIMIT : limit;
        log.info("Getting user tracks. Limit: {}, offset: {}", limit, offset);
        return dao.getUserTracks(usersService.getCurrentUserId(), limit, offset);
    }

    public List<Track> getTracks(Integer limit, Integer offset) {
        limit = limit > MAX_LIMIT ? MAX_LIMIT : limit;
        log.info("Getting tracks. Limit: {}, offset: {}", limit, offset);
        return dao.getTracks(limit, offset);
    }
}
