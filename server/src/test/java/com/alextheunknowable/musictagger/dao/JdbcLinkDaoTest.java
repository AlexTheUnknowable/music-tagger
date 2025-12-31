package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Link;
import com.alextheunknowable.musictagger.model.OriginType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcLinkDaoTest extends BaseDaoTest {
    private static final Link LINK_TRACK_1 = new Link(7, OriginType.TRACK, 1, "https://www.youtube.com/watch?v=-wtiB7oXKsw", 4);
    private static final Link LINK_TRACK_2 = new Link(49, OriginType.TRACK, 43,"https://www.youtube.com/watch?v=0AQMAth2gio", 4);
    private static final Link LINK_TRACK_3 = new Link(150, OriginType.TRACK, 144,"https://www.youtube.com/watch?v=IgeXLLAwNcs", 4);
    private static final Link LINK_ARTIST_1 = new Link(1, OriginType.ARTIST, 1, "https://www.youtube.com/@NightMargins", 4);
    private static final Link LINK_ARTIST_2 = new Link(2, OriginType.ARTIST, 2, "https://www.youtube.com/channel/UC26hbdeqyPRl7VsnK1UhFPw", 4);
    private static final Link LINK_ARTIST_3 = new Link(3, OriginType.ARTIST, 3, "https://www.youtube.com/@Leef6010", 4);
    private static final Link LINK_SOURCE_1 = new Link(4, OriginType.SOURCE, 1, "https://www.youtube.com/playlist?list=PLa73G0dLHZJBI21GOx_jg2btFjEAP9aid", 4);
    private static final Link LINK_SOURCE_2 = new Link(5, OriginType.SOURCE, 2, "https://www.youtube.com/playlist?list=PLKXdyINOQYsb38jn5HGiF6w8X3rph4IIx", 4);
    private static final Link LINK_SOURCE_3 = new Link(6, OriginType.SOURCE, 3, "https://www.youtube.com/playlist?list=PLDOjCqYj3ys3TEe8HCR7_cYH7X7dU28_B", 4);
    private JdbcLinkDao dao;

    @BeforeEach
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcLinkDao(jdbcTemplate);
    }

    @Test
    public void getLinks_returns_correct_list_of_links() {
        List<Link> links = dao.getLinks();

        assertNotNull(links, "getLinks returned a null list of links");
        assertEquals(179, links.size(), "getLinks returned a list with the incorrect number of links");
        assertEquals(LINK_TRACK_1.getUrl(), links.get(6).getUrl(), "getLinks returned a list in incorrect order");
        assertEquals(LINK_TRACK_2.getUrl(), links.get(48).getUrl(), "getLinks returned a list in incorrect order");
        assertEquals(LINK_TRACK_3.getUrl(), links.get(149).getUrl(), "getLinks returned a list in incorrect order");
        assertEquals(LINK_ARTIST_1.getUrl(), links.get(0).getUrl(), "getLinks returned a list in incorrect order");
        assertEquals(LINK_ARTIST_2.getUrl(), links.get(1).getUrl(), "getLinks returned a list in incorrect order");
        assertEquals(LINK_ARTIST_3.getUrl(), links.get(2).getUrl(), "getLinks returned a list in incorrect order");
        assertEquals(LINK_SOURCE_1.getUrl(), links.get(3).getUrl(), "getLinks returned a list in incorrect order");
        assertEquals(LINK_SOURCE_2.getUrl(), links.get(4).getUrl(), "getLinks returned a list in incorrect order");
        assertEquals(LINK_SOURCE_3.getUrl(), links.get(5).getUrl(), "getLinks returned a list in incorrect order");
    }

    @Test
    public void getLinkById_with_valid_id_returns_correct_link() {
        Link actualLink = dao.getLinkById(LINK_TRACK_1.getId());
        assertEquals(LINK_TRACK_1.getUrl(), actualLink.getUrl(), "getLinkById with valid id did not return correct link");
        actualLink = dao.getLinkById(LINK_ARTIST_3.getId());
        assertEquals(LINK_ARTIST_3.getUrl(), actualLink.getUrl(), "getLinkById with valid id did not return correct link");
        actualLink = dao.getLinkById(LINK_SOURCE_2.getId());
        assertEquals(LINK_SOURCE_2.getUrl(), actualLink.getUrl(), "getLinkById with valid id did not return correct link");
    }

    @Test
    public void getLinkById_with_invalid_id_returns_null() {
        Link link = dao.getLinkById(-1);
        assertNull(link, "getLinkById with invalid linkId did not return null link");
    }

    @Test
    public void getLinksByTrackIds_with_valid_track_ids_should_return_correct_links() {
        Map<Integer, List<Link>> track_ids_and_links = dao.getLinksByTrackIds(new ArrayList<>(List.of(1,43,144)));
        assertNotNull(track_ids_and_links, "getLinksByTrackIds returned a null map");
        assertEquals(LINK_TRACK_1.getUrl(), track_ids_and_links.get(1).get(0).getUrl(), "getLinksByTrackIds returned incorrect list of links");
        assertEquals(LINK_TRACK_2.getUrl(), track_ids_and_links.get(43).get(0).getUrl(), "getLinksByTrackIds returned incorrect list of links");
        assertEquals(LINK_TRACK_3.getUrl(), track_ids_and_links.get(144).get(0).getUrl(), "getLinksByTrackIds returned incorrect list of links");
    }

    @Test
    public void getLinksByTrackIds_with_invalid_track_ids_should_return_empty_map() {
        Map<Integer, List<Link>> track_ids_and_links = dao.getLinksByTrackIds(new ArrayList<>(List.of(-1)));
        assertNotNull(track_ids_and_links, "getLinksByTrackIds with invalid track IDs returned a null map");
        assertEquals(new HashMap<Integer, List<Link>>(), track_ids_and_links, "getLinksByTrackIds with invalid track IDs did not return empty map");
    }

    @Test
    public void getLinksByTrackIds_with_empty_track_ids_list_should_return_empty_map() {
        Map<Integer, List<Link>> track_ids_and_links = dao.getLinksByTrackIds(new ArrayList<>());
        assertNotNull(track_ids_and_links, "getLinksByTrackIds with empty track IDs list returned a null map");
        assertEquals(new HashMap<Integer, List<Link>>(), track_ids_and_links, "getLinksByTrackIds with empty track IDs list did not return empty map");
    }

    @Test
    public void getLinksByTrackIds_with_null_track_ids_list_should_return_empty_map() {
        Map<Integer, List<Link>> track_ids_and_links = dao.getLinksByTrackIds(null);
        assertNotNull(track_ids_and_links, "getLinksByTrackIds with null track IDs list returned a null map");
        assertEquals(new HashMap<Integer, List<Link>>(), track_ids_and_links, "getLinksByTrackIds with null track IDs list did not return empty map");
    }

    @Test
    public void getLinksByTrackIds_with_null_track_ids_should_return_correct_map() {
        List<Integer> ints = new ArrayList<>();
        ints.add(null);
        ints.add(1);
        Map<Integer, List<Link>> track_ids_and_links = dao.getLinksByTrackIds(ints);
        assertNotNull(track_ids_and_links, "getLinksByTrackIds with null track IDs returned a null map");
        assertEquals(1, track_ids_and_links.size(), "getLinksByTrackIds with null track IDs returned map of incorrect size");
        assertEquals(LINK_TRACK_1.getUrl(), track_ids_and_links.get(1).get(0).getUrl(), "getLinksByTrackIds returned incorrect list of links");
    }

    @Test
    public void createLink_should_create_link() {
        Link newLink = new Link(999, OriginType.TRACK, 1, "https://open.spotify.com/track/0pWaxKeh0erjYtDsFQOJSs?si=5e921d7d89f34765", 4);

        Link link = dao.createLink(newLink);
        assertNotNull(link, "Call to create should return non-null link");

        Link actualLink = dao.getLinkById(link.getId());
        assertNotNull(actualLink, "Call to getLinkById after call to create should return non-null link");

        newLink.setId(actualLink.getId());
        assertEquals(newLink.getUrl(), actualLink.getUrl());
    }

    @Test
    public void createLink_with_no_url_should_throw_error() {
        try {
            dao.createLink(new Link(999, OriginType.TRACK, 1, null, 1));
            fail("Expected createLink() with null name to throw DaoException, but it didn't throw any exception");
        } catch (DaoException e) {
            // expected condition
        } catch (Exception e) {
            fail("Expected createLink() with null name to throw DaoException, but threw a different exception");
        }
    }
}