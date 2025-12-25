package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Source;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcSourceDao implements SourceDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcSourceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Source> getSources() {
        List<Source> sources = new ArrayList<>();
        String sql = "SELECT * FROM source ORDER BY id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Source source = mapRowToSource(results);
                sources.add(source);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sources;
    }

    @Override
    public Source getSourceById(int sourceId) {
        Source source = null;
        String sql = "SELECT * FROM source WHERE id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sourceId);
            if (results.next()) {
                source = mapRowToSource(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return source;
    }

    @Override
    public List<Source> getSourcesByName(String name) {
        if (name == null) name = "";
        List<Source> sources = new ArrayList<>();
        String sql = "SELECT * FROM source WHERE LOWER(name) LIKE LOWER(?)";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + name + "%");
            while (results.next()) {
                sources.add(mapRowToSource(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sources;
    }

    @Override
    public Source createSource(Source newSource) {
        Source source = null;
        String insertSourceSql = "INSERT INTO source (name, uploader_id) VALUES (?, ?) RETURNING id";
        try {
            int sourceId = jdbcTemplate.queryForObject(insertSourceSql, int.class, newSource.getName(), newSource.getUploaderId());
            source = getSourceById(sourceId);
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
        return source;
    }

    @Override
    public Source updateSource(Source source) {
        Source updatedSource = null;
        String updateSourceSql = "UPDATE source SET name = ?, uploader_id = ? WHERE id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(updateSourceSql, source.getName(), source.getUploaderId(), source.getId());
            if (numberOfRows == 0) {
                throw new DaoException("0 rows affected, expected at least 1");
            } else {
                updatedSource = getSourceById(source.getId());
            }
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return updatedSource;
    }

    @Override
    public int deleteSourceById(int sourceId) {
        int numberOfRows = 0;
        //String deleteItemSql = "DELETE FROM item WHERE card_id = ?;";
        //String deleteCardTypeSql = "DELETE FROM card_type WHERE card_id = ?;";
        String deleteSourceSql = "DELETE FROM source WHERE id = ?;";
        try {
            //jdbcTemplate.update(deleteItemSql, cardId);
            //jdbcTemplate.update(deleteCardTypeSql, cardId);
            numberOfRows = jdbcTemplate.update(deleteSourceSql, sourceId);
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return numberOfRows;
    }

    private Source mapRowToSource(SqlRowSet rs) {
        Source source = new Source();
        source.setId(rs.getInt("id"));
        source.setName(rs.getString("name"));
        source.setUploaderId(rs.getInt("uploader_id"));
        return source;
    }
}
