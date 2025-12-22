package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Alias;
import com.alextheunknowable.musictagger.model.OriginType;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.*;

public class JdbcAliasDao implements AliasDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcAliasDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Alias> getAliases() {
        List<Alias> aliases = new ArrayList<>();
        String sql = "SELECT * FROM alias ORDER BY id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Alias alias = mapRowToAlias(results);
                aliases.add(alias);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return aliases;
    }

    @Override
    public Alias getAliasById(int aliasId) {
        Alias alias = null;
        String sql = "SELECT * FROM alias WHERE id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, aliasId);
            if (results.next()) {
                alias = mapRowToAlias(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return alias;
    }

    @Override
    public Map<Integer, List<Alias>> getAliasesByTrackIds(List<Integer> trackIds) {
        Map<Integer, List<Alias>> aliasesByTrackId = new HashMap<>();
        if (trackIds == null || trackIds.isEmpty()) return aliasesByTrackId;

        String placeholders = String.join(",", Collections.nCopies(trackIds.size(), "?"));
        String sql = """
        SELECT *
        FROM alias
        WHERE track_id IN (%s)
        ORDER BY track_id, id
        """.formatted(placeholders);

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, trackIds.toArray());
            while (results.next()) {
                Alias alias = mapRowToAlias(results);
                int trackId = results.getInt("track_id");
                aliasesByTrackId
                        .computeIfAbsent(trackId, k -> new ArrayList<>())
                        .add(alias);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return aliasesByTrackId;
    }


    @Override
    public Alias createAlias(Alias newAlias) {
        Alias alias = null;
        String insertAliasSql = "INSERT INTO alias (track_id, alias, uploader_id) VALUES (?, ?, ?) RETURNING id";
        try {
            int aliasId = jdbcTemplate.queryForObject(insertAliasSql, int.class, newAlias.getTrackId(), newAlias.getAlias(), newAlias.getUploaderId());
            alias = getAliasById(aliasId);
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
        return alias;
    }

    @Override
    public Alias updateAlias(Alias alias) {
        Alias updatedAlias = null;
        String updateAliasSql = "UPDATE alias SET track_id = ?, alias = ?, uploader_id = ? WHERE id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(updateAliasSql, alias.getTrackId(), alias.getAlias(), alias.getUploaderId(), alias.getId());
            if (numberOfRows == 0) {
                throw new DaoException("0 rows affected, expected at least 1");
            } else {
                updatedAlias = getAliasById(alias.getId());
            }
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return updatedAlias;
    }

    @Override
    public int deleteAliasById(int aliasId) {
        int numberOfRows = 0;
        //String deleteItemSql = "DELETE FROM item WHERE card_id = ?;";
        //String deleteCardTypeSql = "DELETE FROM card_type WHERE card_id = ?;";
        String deleteAliasSql = "DELETE FROM alias WHERE id = ?;";
        try {
            //jdbcTemplate.update(deleteItemSql, cardId);
            //jdbcTemplate.update(deleteCardTypeSql, cardId);
            numberOfRows = jdbcTemplate.update(deleteAliasSql, aliasId);
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return numberOfRows;
    }

    private Alias mapRowToAlias(SqlRowSet rs) {
        Alias alias = new Alias();
        alias.setId(rs.getInt("id"));
        alias.setTrackId(rs.getInt("track_id"));
        alias.setAlias(rs.getString("alias"));
        alias.setUploaderId(rs.getInt("uploader_id"));
        return alias;
    }
}
