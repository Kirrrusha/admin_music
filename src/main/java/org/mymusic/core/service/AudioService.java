package org.mymusic.core.service;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mymusic.core.dao.AudioDao;
import org.mymusic.core.domain.Track;
import org.mymusic.core.domain.UserFile;
import org.mymusic.core.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AudioService {

    private final AudioDao dao;
    private final UsersService usersService;

    @Transactional
    public Track upload(MultipartFile file) throws Exception {
        log.info("Uploading file {}", file.getOriginalFilename());
        Integer userId = usersService.getCurrentUserId();
        String uuid = dao.saveFile(file, userId);
        File temp = File.createTempFile(uuid, file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(temp);
        fos.write(file.getBytes());
        fos.close();
        temp.deleteOnExit();
        Mp3File mp3file = new Mp3File(temp);
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            Track track = Track.builder()
                    .userId(userId)
                    .fileUUID(uuid)
                    .artist(unknownIfNull(id3v2Tag.getArtist()))
                    .title(unknownIfNull(id3v2Tag.getTitle()))
                    .genre(unknownIfNull(id3v2Tag.getGenreDescription()))
                    .duration(mp3file.getLengthInSeconds())
                    .build();
            log.info("Saving track: {}", track);
            track.setId(dao.saveTrack(track));
            return track;
        } else {
            throw new IllegalStateException("Failed to read Id3v1Tag");
        }
    }

    public UserFile getAudioFile(String uuid) {
        log.info("Getting file {}", uuid);
        return dao.getAudioFile(uuid).orElseThrow(() -> new ServiceException.FileNotFoundException("File not found"));
    }

    private String unknownIfNull(String s) {
        return s == null ? "Unknown" : s;
    }
}
