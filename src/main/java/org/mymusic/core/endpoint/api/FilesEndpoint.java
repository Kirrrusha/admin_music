package org.mymusic.core.endpoint.api;

import lombok.RequiredArgsConstructor;
import org.mymusic.core.domain.UserFile;
import org.mymusic.core.service.FilesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/files")
@RequiredArgsConstructor
public class FilesEndpoint {

    private final FilesService service;

    @GetMapping
    public List<UserFile> getUserFiles() {
        return service.getUserFiles();
    }
}
