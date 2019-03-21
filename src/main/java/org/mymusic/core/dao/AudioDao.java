package org.mymusic.core.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mymusic.core.domain.Track;
import org.mymusic.core.domain.UserFile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AudioDao {

    private final NamedParameterJdbcTemplate jdbc;

    public String saveFile(MultipartFile file, Integer userId) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String sql = "INSERT INTO files(uuid, name, bytes, user_id) VALUES (:uuid, :name, :bytes, :user_id)";
        Map<String, Object> params = new HashMap<>();
        params.put("uuid", uuid);
        params.put("name", file.getOriginalFilename());
        params.put("bytes", file.getBytes());
        params.put("user_id", userId);
        jdbc.update(sql, params);
        return uuid;
    }

    public Long saveTrack(Track track) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", track.getUserId());
        params.put("file_uuid", track.getFileUUID());
        params.put("artist", track.getArtist());
        params.put("title", track.getTitle());
        params.put("duration", track.getDuration());
        params.put("genre", track.getGenre());
        return new SimpleJdbcInsert(jdbc.getJdbcTemplate()).withTableName("tracks")
                .usingGeneratedKeyColumns("id")
                .usingColumns(params.keySet().toArray(new String[0]))
                .executeAndReturnKey(params)
                .longValue();
    }

    public Optional<UserFile> getAudioFile(String uuid) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject("SELECT * FROM files WHERE uuid = :uuid",
                            Collections.singletonMap("uuid", uuid),
                            (rs, rowNum) -> UserFile.builder()
                                .uuid(uuid)
                                .filename(rs.getString("name"))
                                .bytes(rs.getBytes("bytes"))
                                .build()
                    ));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }
}
