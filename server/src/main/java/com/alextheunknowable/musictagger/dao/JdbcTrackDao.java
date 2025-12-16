package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Track;
import com.alextheunknowable.musictagger.model.TrackSearchCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcTrackDao implements TrackDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTrackDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Track> getTracks() {
        List<Track> tracks = new ArrayList<>();
        String sql = "SELECT * FROM track ORDER BY id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                tracks.add(mapRowToTrack(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return tracks;
    }

    @Override
    public List<Track> getTracks(TrackSearchCriteria tsc, Integer userId) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT DISTINCT t.* FROM track t ");

        // Build dynamic joins
        appendJoins(sql, tsc);

        // Build dynamic WHERE clauses
        List<String> whereClauses = buildWhereClauses(tsc, userId, params);
        if (!whereClauses.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", whereClauses));
        }

        // Build HAVING clauses for tag counts
        List<String> havingClauses = buildHavingClauses(tsc);
        if (!havingClauses.isEmpty()) {
            sql.append(" GROUP BY t.id HAVING ").append(String.join(" AND ", havingClauses));
        }

        sql.append(" ORDER BY t.name;");

        List<Track> tracks = new ArrayList<>();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql.toString(), params.toArray());
            while (results.next()) {
                tracks.add(mapRowToTrack(results));
            }
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return tracks;
    }


    @Override
    public Track getTrackById(int trackId) {
        Track track = null;
        String sql = "SELECT * FROM track WHERE id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, trackId);
            if (results.next()) track = mapRowToTrack(results);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return track;
    }

    @Override
    public List<Track> getTracksByName(String name) {
        if (name == null) name = "";
        List<Track> tracks = new ArrayList<>();
        String sql = "SELECT * FROM track WHERE LOWER(name) LIKE LOWER(?)";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + name + "%");
            while (results.next()) {
                tracks.add(mapRowToTrack(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return tracks;
    }

    @Override
    public List<Track> getTracksByArtist(int artistId) {
        List<Track> tracks = new ArrayList<>();
        String sql = "SELECT * FROM track " +
                     "JOIN track_artist ON track.id = track_artist.track_id " +
                     "WHERE track_artist.artist_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, artistId);
            while (results.next()) {
                tracks.add(mapRowToTrack(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return tracks;
    }

    @Override
    public List<Track> getTracksBySource(int sourceId) {
        List<Track> tracks = new ArrayList<>();
        String sql = "SELECT * FROM track WHERE source_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sourceId);
            while (results.next()) {
                tracks.add(mapRowToTrack(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return tracks;
    }

    @Override
    public List<Track> getTracksByGlobalTags(List<Integer> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) throw new DaoException("Tried to fetch by tag with null or empty tag list");
        List<Track> tracks = new ArrayList<>();
        String placeholders = String.join(",", Collections.nCopies(tagIds.size(), "?"));
        String sql = "SELECT track.* FROM track " +
                     "JOIN track_tag_counter AS ttc ON track.id = ttc.track_id " +
                     "WHERE ttc.tag_id IN (" + placeholders + ") " +
                     "GROUP BY track.id " +
                     "HAVING COUNT(DISTINCT ttc.tag_id) = ? " +
                     "ORDER BY SUM(ttc.vote_count) DESC;";
        List<Integer> params = new ArrayList<>(tagIds);
        params.add(tagIds.size());
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, params.toArray());
            while (results.next()) {
                tracks.add(mapRowToTrack(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return tracks;
    }

    @Override
    public List<Track> getTracksByUserTags(int userId, List<Integer> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) throw new DaoException("Tried to fetch by tag with null or empty tag list");
        List<Track> tracks = new ArrayList<>();
        String placeholders = String.join(",", Collections.nCopies(tagIds.size(), "?"));
        String sql = "SELECT track.* FROM track " +
                     "JOIN track_tag AS tt ON track.id = tt.track_id " +
                     "WHERE tt.user_id = ? AND tt.tag_id IN (" + placeholders + ") " +
                     "GROUP BY track.id " +
                     "HAVING COUNT(DISTINCT tt.tag_id) = ? " +
                     "ORDER BY track.name;"; // TODO: change to time created
        List<Integer> params = new ArrayList<>();
        params.add(userId);
        params.addAll(tagIds);
        params.add(tagIds.size());
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, params.toArray());
            while (results.next()) {
                tracks.add(mapRowToTrack(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return tracks;
    }

    @Override
    public Track createTrack(Track newTrack) {
        Track track = null;
        String insertTrackSql = "INSERT INTO track (name, source_id, uploader_id) VALUES (?, ?, ?) RETURNING id";
        try {
            int trackId = jdbcTemplate.queryForObject(insertTrackSql, int.class, newTrack.getName(), newTrack.getSourceId(), newTrack.getUploaderId());
            track = getTrackById(trackId);
        }
        catch (NullPointerException e) {
            throw new DaoException("Jdbc query returned a null object", e);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return track;
    }

    @Override
    public Track updateTrack(Track track) {
        Track updatedTrack = null;
        String updateTrackSql = "UPDATE track SET name = ?, source_id = ?, uploader_id = ? WHERE id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(updateTrackSql, track.getName(), track.getSourceId(), track.getUploaderId(), track.getId());
            if (numberOfRows == 0) {
                throw new DaoException("0 rows affected, expected at least 1");
            } else {
                updatedTrack = getTrackById(track.getId());
            }
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return updatedTrack;
    }

    @Override
    public int deleteTrackById(int trackId) {
        int numberOfRows = 0;
        //String deleteItemSql = "DELETE FROM item WHERE card_id = ?;";
        //String deleteCardTypeSql = "DELETE FROM card_type WHERE card_id = ?;";
        String deleteTrackSql = "DELETE FROM track WHERE id = ?;";
        try {
            //jdbcTemplate.update(deleteItemSql, cardId);
            //jdbcTemplate.update(deleteCardTypeSql, cardId);
            numberOfRows = jdbcTemplate.update(deleteTrackSql, trackId);
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return numberOfRows;
    }

    private Track mapRowToTrack(SqlRowSet rs) {
        Track track = new Track();
        track.setId(rs.getInt("id"));
        track.setName(rs.getString("name"));
        track.setSourceId(rs.getInt("source_id"));
        track.setUploaderId(rs.getInt("uploader_id"));
        return track;
    }

    private void appendJoins(StringBuilder sql, TrackSearchCriteria tsc) {
        if (tsc.getArtistId() != null) {
            sql.append("JOIN track_artist ta ON t.id = ta.track_id ");
        }
        if (tsc.getGlobalTagIds() != null && !tsc.getGlobalTagIds().isEmpty()) {
            sql.append("JOIN track_tag_counter ttc ON t.id = ttc.track_id ");
        }
        if (tsc.getUserTagIds() != null && !tsc.getUserTagIds().isEmpty()) {
            sql.append("JOIN track_tag ut ON t.id = ut.track_id ");
        }
    }

    private List<String> buildWhereClauses(TrackSearchCriteria tsc, Integer userId, List<Object> params) {
        List<String> whereClauses = new ArrayList<>();

        if (tsc.getArtistId() != null) {
            whereClauses.add("ta.artist_id = ?");
            params.add(tsc.getArtistId());
        }

        if (tsc.getSourceId() != null) {
            whereClauses.add("t.source_id = ?");
            params.add(tsc.getSourceId());
        }

        if (tsc.getName() != null && !tsc.getName().isBlank()) {
            whereClauses.add("LOWER(t.name) LIKE ?");
            params.add("%" + tsc.getName().toLowerCase() + "%");
        }

        if (tsc.getGlobalTagIds() != null && !tsc.getGlobalTagIds().isEmpty()) {
            String placeholders = String.join(",", Collections.nCopies(tsc.getGlobalTagIds().size(), "?"));
            whereClauses.add("ttc.tag_id IN (" + placeholders + ")");
            params.addAll(tsc.getGlobalTagIds());
        }

        if (userId != null && tsc.getUserTagIds() != null && !tsc.getUserTagIds().isEmpty()) {
            String placeholders = String.join(",", Collections.nCopies(tsc.getUserTagIds().size(), "?"));
            whereClauses.add("ut.user_id = ?");
            whereClauses.add("ut.tag_id IN (" + placeholders + ")");
            params.add(userId);
            params.addAll(tsc.getUserTagIds());
        }

        return whereClauses;
    }

    private List<String> buildHavingClauses(TrackSearchCriteria tsc) {
        List<String> havingClauses = new ArrayList<>();

        if (tsc.getGlobalTagIds() != null && !tsc.getGlobalTagIds().isEmpty()) {
            havingClauses.add("COUNT(DISTINCT ttc.tag_id) = " + tsc.getGlobalTagIds().size());
        }

        if (tsc.getUserTagIds() != null && !tsc.getUserTagIds().isEmpty()) {
            havingClauses.add("COUNT(DISTINCT ut.tag_id) = " + tsc.getUserTagIds().size());
        }

        return havingClauses;
    }

}
