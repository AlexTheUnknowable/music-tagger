package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Artist;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcArtistDao implements ArtistDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcArtistDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Artist> getArtists() {
        List<Artist> artists = new ArrayList<>();
        String sql = "SELECT * FROM artist ORDER BY id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Artist artist = mapRowToArtist(results);
                artists.add(artist);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return artists;
    }

    @Override
    public Artist getArtistById(int artistId) {
        Artist artist = null;
        String sql = "SELECT * FROM artist WHERE id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, artistId);
            if (results.next()) {
                artist = mapRowToArtist(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return artist;
    }

    @Override
    public Artist getArtistByName(String name) {
        if (name == null) name = "";
        Artist artist = null;
        String sql = "SELECT * FROM artist WHERE name = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            if (results.next()) artist = mapRowToArtist(results);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return artist;
    }

    @Override
    public Artist createArtist(Artist newArtist) {
        Artist artist = null;
        String insertArtistSql = "INSERT INTO artist (name, uploader_id) VALUES (?, ?) RETURNING id";
        //HEY!! THIS SHOULD BE IN THE SERVICE!!!!!!!!
        //if (newArtist.getName() == null || newArtist.getName().isBlank()) throw new DaoException("Artist cannot be created with null/blank name");
        try {
            int artistId = jdbcTemplate.queryForObject(insertArtistSql, int.class, newArtist.getName(), newArtist.getUploaderId());
            artist = getArtistById(artistId);
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
        return artist;
    }

    @Override
    public Artist updateArtist(Artist artist) {
        Artist updatedArtist = null;
        String updateArtistSql = "UPDATE artist SET name = ?, uploader_id = ? WHERE id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(updateArtistSql, artist.getName(), artist.getUploaderId(), artist.getId());
            if (numberOfRows == 0) {
                throw new DaoException("0 rows affected, expected at least 1");
            } else {
                updatedArtist = getArtistById(artist.getId());
            }
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return updatedArtist;
    }

    @Override
    public int deleteArtistById(int artistId) {
        int numberOfRows = 0;
        //String deleteItemSql = "DELETE FROM item WHERE card_id = ?;";
        //String deleteCardTypeSql = "DELETE FROM card_type WHERE card_id = ?;";
        String deleteArtistSql = "DELETE FROM artist WHERE id = ?;";
        try {
            //jdbcTemplate.update(deleteItemSql, cardId);
            //jdbcTemplate.update(deleteCardTypeSql, cardId);
            numberOfRows = jdbcTemplate.update(deleteArtistSql, artistId);
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return numberOfRows;
    }

    private Artist mapRowToArtist(SqlRowSet rs) {
        Artist artist = new Artist();
        artist.setId(rs.getInt("id"));
        artist.setName(rs.getString("name"));
        artist.setUploaderId(rs.getInt("uploader_id"));
        return artist;
    }
}
