package org.mymusic.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mymusic.core.dao.FilesDao;
import org.mymusic.core.domain.UserFile;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilesService {

    private final FilesDao dao;
    private final UsersService usersService;

    public List<UserFile> getUserFiles() {
        log.info("Getting files list");
        return dao.getUserFiles(usersService.getCurrentUserId());
    }
}
