package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcTrackArtistDao implements TrackArtistDao {
    private final JdbcTemplate jdbcTemplate;
    public JdbcTrackArtistDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addArtistToTrack(int trackId, int artistId) {
        String sql = "INSERT INTO track_artist (track_id, artist_id) VALUES (?, ?);";
        try {
            jdbcTemplate.update(sql, trackId, artistId);
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
    }

    @Override
    public int removeArtistFromTrack(int trackId, int artistId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM track_artist WHERE track_id = ? AND artist_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, trackId, artistId);
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return numberOfRows;
    }

    @Override
    public List<Integer> getArtistIdsForTrack(int trackId) {
        List<Integer> artistIds = new ArrayList<>();
        String sql = "SELECT artist_id FROM track_artist WHERE track_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, trackId);
            while (results.next()) {
                artistIds.add(results.getInt("artist_id"));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return artistIds;
    }
}
