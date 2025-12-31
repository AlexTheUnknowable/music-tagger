package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Link;
import com.alextheunknowable.musictagger.model.OriginType;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JdbcLinkDao implements LinkDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcLinkDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Link> getLinks() {
        List<Link> links = new ArrayList<>();
        String sql = "SELECT * FROM link ORDER BY id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Link link = mapRowToLink(results);
                links.add(link);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return links;
    }

    @Override
    public Link getLinkById(int linkId) {
        Link link = null;
        String sql = "SELECT * FROM link WHERE id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, linkId);
            if (results.next()) {
                link = mapRowToLink(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return link;
    }

    @Override
    public Map<Integer, List<Link>> getLinksByTrackIds(List<Integer> trackIds) {
        Map<Integer, List<Link>> linksByTrackId = new HashMap<>();
        if (trackIds == null || trackIds.isEmpty()) return linksByTrackId;

        String placeholders = String.join(",", Collections.nCopies(trackIds.size(), "?"));
        String sql = """
        SELECT link.*
        FROM link
        WHERE origin_type = 'TRACK' AND origin_id IN (%s)
        ORDER BY origin_id, id
        """.formatted(placeholders);

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, trackIds.toArray());
            while (results.next()) {
                Link link = mapRowToLink(results);
                int trackId = results.getInt("origin_id");
                linksByTrackId
                        .computeIfAbsent(trackId, k -> new ArrayList<>())
                        .add(link);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return linksByTrackId;
    }

    @Override
    public Link createLink(Link newLink) {
        Link link = null;
        String insertLinkSql = "INSERT INTO link (origin_type, origin_id, url, uploader_id) VALUES (?, ?, ?, ?) RETURNING id";
        try {
            int linkId = jdbcTemplate.queryForObject(insertLinkSql, int.class, newLink.getOriginType().name(), newLink.getOriginId(), newLink.getUrl(), newLink.getUploaderId());
            link = getLinkById(linkId);
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
        return link;
    }

    @Override
    public Link updateLink(Link link) {
        Link updatedLink = null;
        String updateLinkSql = "UPDATE link SET origin_type = ?, origin_id = ?, url = ?, uploader_id = ? WHERE id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(updateLinkSql, link.getOriginType(), link.getOriginId(), link.getUrl(), link.getUploaderId(), link.getId());
            if (numberOfRows == 0) {
                throw new DaoException("0 rows affected, expected at least 1");
            } else {
                updatedLink = getLinkById(link.getId());
            }
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return updatedLink;
    }

    @Override
    public int deleteLinkById(int linkId) {
        int numberOfRows = 0;
        //String deleteItemSql = "DELETE FROM item WHERE card_id = ?;";
        //String deleteCardTypeSql = "DELETE FROM card_type WHERE card_id = ?;";
        String deleteLinkSql = "DELETE FROM link WHERE id = ?;";
        try {
            //jdbcTemplate.update(deleteItemSql, cardId);
            //jdbcTemplate.update(deleteCardTypeSql, cardId);
            numberOfRows = jdbcTemplate.update(deleteLinkSql, linkId);
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
        return numberOfRows;
    }

    private Link mapRowToLink(SqlRowSet rs) {
        Link link = new Link();
        link.setId(rs.getInt("id"));
        link.setOriginType(OriginType.valueOf(rs.getString("origin_type")));
        link.setOriginId(rs.getInt("origin_id"));
        link.setUrl(rs.getString("url"));
        link.setUploaderId(rs.getInt("uploader_id"));
        return link;
    }
}
