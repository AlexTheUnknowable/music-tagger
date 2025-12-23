package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.*;

public class JdbcTagDao implements TagDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTagDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM tag ORDER BY id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Tag tag = mapRowToTag(results);
                tags.add(tag);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return tags;
    }

    @Override
    public Tag getTagById(int tagId) {
        Tag tag = null;
        String sql = "SELECT * FROM tag WHERE id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tagId);
            if (results.next()) {
                tag = mapRowToTag(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return tag;
    }

    @Override
    public List<Tag> getTagsByName(String name) {
        if (name == null) name = "";
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM tag WHERE LOWER(name) LIKE LOWER(?)";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + name + "%");
            while (results.next()) {
                Tag tag = mapRowToTag(results);
                tags.add(tag);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return tags;
    }

    @Override
    public Map<Integer, List<Tag>> getTagsByTrackIds(List<Integer> trackIds) {
        Map<Integer, List<Tag>> tagsByTrackId = new HashMap<>();
        if (trackIds == null || trackIds.isEmpty()) return tagsByTrackId;

        String placeholders = String.join(",", Collections.nCopies(trackIds.size(), "?"));

        String sql = """
        SELECT
            t.id,
            t.name,
            t.uploader_id,
            ttc.track_id,
            ttc.vote_count
        FROM track_tag_counter ttc
        JOIN tag t ON t.id = ttc.tag_id
        WHERE ttc.track_id IN (%s)
        ORDER BY ttc.track_id, ttc.vote_count DESC, t.name
        """.formatted(placeholders);

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, trackIds.toArray());
            while (results.next()) {
                Tag tag = mapRowToTag(results); // map id, name, uploader_id
                int trackId = results.getInt("track_id");

                tagsByTrackId
                        .computeIfAbsent(trackId, k -> new ArrayList<>())
                        .add(tag);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return tagsByTrackId;
    }


    @Override
    public Tag createTag(Tag newTag) {
        Tag tag = null;
        String insertTagSql = "INSERT INTO tag (name) VALUES (?) RETURNING id";
        try {
            int tagId = jdbcTemplate.queryForObject(insertTagSql, int.class, newTag.getName());
            tag = getTagById(tagId);
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
        return tag;
    }

    @Override
    public Tag updateTag(Tag tag) {
        Tag updatedTag = null;
        String updateTagSql = "UPDATE tag SET name = ? WHERE id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(updateTagSql, tag.getName(), tag.getId());
            if (numberOfRows == 0) {
                throw new DaoException("0 rows affected, expected at least 1");
            } else {
                updatedTag = getTagById(tag.getId());
            }
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return updatedTag;
    }

    @Override
    public int deleteTagById(int tagId) {
        int numberOfRows = 0;
        //String deleteItemSql = "DELETE FROM item WHERE card_id = ?;";
        //String deleteCardTypeSql = "DELETE FROM card_type WHERE card_id = ?;";
        String deleteTagSql = "DELETE FROM tag WHERE id = ?;";
        try {
            //jdbcTemplate.update(deleteItemSql, cardId);
            //jdbcTemplate.update(deleteCardTypeSql, cardId);
            numberOfRows = jdbcTemplate.update(deleteTagSql, tagId);
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return numberOfRows;
    }

    private Tag mapRowToTag(SqlRowSet rs) {
        Tag tag = new Tag();
        tag.setId(rs.getInt("id"));
        tag.setName(rs.getString("name"));
        tag.setUploaderId(rs.getInt("uploader_id"));
        return tag;
    }
}
