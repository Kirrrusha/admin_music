package org.mymusic.core.dao;

import lombok.RequiredArgsConstructor;
import org.mymusic.core.domain.Track;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TracksDao {

    private final NamedParameterJdbcTemplate jdbc;

    public List<Track> getUserTracks(Integer userId, Integer limit, Integer offset) {
        String sql = "SELECT * FROM tracks " +
                "WHERE user_id = :user_id AND deleted = FALSE " +
                "ORDER BY created_at DESC " +
                "LIMIT :limit OFFSET :offset";
        Map<String, Object> params = getParameters(limit, offset);
        params.put("user_id", userId);
        return jdbc.query(sql, params, this::mapRow);
    }

    public List<Track> getTracks(Integer limit, Integer offset) {
        String sql = "SELECT * FROM tracks " +
                "WHERE deleted = FALSE " +
                "ORDER BY created_at DESC " +
                "LIMIT :limit OFFSET :offset";
        return jdbc.query(sql, getParameters(limit, offset), this::mapRow);
    }

    private Map<String, Object> getParameters(Integer limit, Integer offset) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);
        return params;
    }

    private Track mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Track.builder()
                .id(rs.getLong("id"))
                .artist(rs.getString("artist"))
                .title(rs.getString("title"))
                .genre(rs.getString("genre"))
                .fileUUID(rs.getString("file_uuid"))
                .duration(rs.getLong("duration"))
                .build();
    }
}
