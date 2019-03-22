package org.mymusic.core.dao;

import lombok.RequiredArgsConstructor;
import org.mymusic.core.domain.UserFile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

import static java.util.Collections.singletonMap;

@Repository
@RequiredArgsConstructor
public class FilesDao {

    private final NamedParameterJdbcTemplate jdbc;

    public List<UserFile> getUserFiles(Integer userId) {
        String sql = "SELECT uuid, created_at, name FROM files WHERE user_id = :id ORDER BY name";
        return jdbc.query(sql, singletonMap("id", userId), (rs, rowNum) -> UserFile.builder()
                .uuid(rs.getString("uuid"))
                .filename(rs.getString("name"))
                .createdAt(rs.getTimestamp("created_at"))
                .build());
    }
}
