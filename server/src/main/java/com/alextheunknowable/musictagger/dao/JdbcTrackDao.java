package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Track;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        String insertTrackSql = "INSERT INTO track (name, source_id, uploaded_by) VALUES (?, ?, ?) RETURNING id";
        //HEY!! THIS (the commented out code below) LOGIC SHOULD BE IN THE SERVICE!!!!!!!! i think
        //if (newTrack.getName() == null || newTrack.getName().isBlank()) throw new DaoException("Track cannot be created with null/blank name");
        try {
            int trackId = jdbcTemplate.queryForObject(insertTrackSql, int.class, newTrack.getName(), newTrack.getSourceId(), newTrack.getUploadedByUserId());
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
        String updateTrackSql = "UPDATE track SET name = ?, source_id = ?, uploaded_by = ? WHERE id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(updateTrackSql, track.getName(), track.getSourceId(), track.getUploadedByUserId(), track.getId());
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
        track.setUploadedByUserId(rs.getInt("uploaded_by"));
        return track;
    }
}
