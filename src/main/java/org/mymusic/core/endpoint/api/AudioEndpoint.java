package org.mymusic.core.endpoint.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mymusic.core.domain.Track;
import org.mymusic.core.exception.ServiceException;
import org.mymusic.core.service.AudioService;
import org.mymusic.core.utils.FileUtils;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/audio")
@RequiredArgsConstructor
public class AudioEndpoint {

    private final AudioService service;

    @PostMapping("/upload")
    public Track uploadAudioFile(@RequestParam MultipartFile file) {
        try {
            return service.upload(file);
        } catch (Exception ex) {
            log.error("Failed to upload file {}, error: {}", file.getOriginalFilename(), ex);
            throw new ServiceException.FileUploadException(file.getOriginalFilename());
        }
    }

    @GetMapping("/download")
    public HttpEntity<byte[]> downloadAudioFile(@RequestParam String uuid) {
        return FileUtils.toHttpEntity(service.getAudioFile(uuid));
    }
}
