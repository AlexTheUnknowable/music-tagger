package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Track;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
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
                Track track = mapRowToTrack(results);
                tracks.add(track);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return tracks;
    }

    @Override
    public Track getTrackById(int trackId) {
        Track track = null;
        String sql = "SELECT * FROM track WHERE id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, trackId);
            if (results.next()) {
                track = mapRowToTrack(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return track;
    }

    @Override
    public Track getTrackByName(String name) {
        if (name == null) name = "";
        Track track = null;
        String sql = "SELECT * FROM track WHERE name = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            if (results.next()) track = mapRowToTrack(results);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return track;
    }

    @Override
    public Track createTrack(Track newTrack) {
        Track track = null;
        String insertTrackSql = "INSERT INTO track (name, source_id, uploaded_by) VALUES (?, ?, ?) RETURNING id";
        //HEY!! THIS SHOULD BE IN THE SERVICE!!!!!!!! i think
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
