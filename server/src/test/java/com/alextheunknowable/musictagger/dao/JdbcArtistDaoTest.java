package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcArtistDaoTest extends BaseDaoTest {
    private static final Artist ARTIST_1 = new Artist(1, "Nightmargin", 4);
    private static final Artist ARTIST_2 = new Artist(2, "Toby Fox", 4);
    private static final Artist ARTIST_3 = new Artist(3, "Leef 6010", 1);
    private JdbcArtistDao dao;

    @BeforeEach
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcArtistDao(jdbcTemplate);
    }

    @Test
    public void getArtists_returns_correct_list_of_artists() {
        List<Artist> artists = dao.getArtists();

        assertNotNull(artists, "getArtists returned a null list of artists");
        assertEquals(3, artists.size(), "getArtists returned a list with the incorrect number of artists");
        assertEquals(ARTIST_1.getName(), artists.get(0).getName(), "getArtists returned a list in incorrect order");
        assertEquals(ARTIST_2.getName(), artists.get(1).getName(), "getArtists returned a list in incorrect order");
        assertEquals(ARTIST_3.getName(), artists.get(2).getName(), "getArtists returned a list in incorrect order");
    }

    @Test
    public void getArtistById_with_valid_id_returns_correct_artist() {
        Artist actualArtist = dao.getArtistById(ARTIST_1.getId());
        assertEquals(ARTIST_1.getName(), actualArtist.getName(), "getArtistById with valid id did not return correct artist");
    }

    @Test
    public void getArtistById_with_invalid_id_returns_null() {
        Artist artist = dao.getArtistById(-1);
        assertNull(artist, "getArtistById with invalid artistId did not return null artist");
    }

    @Test
    public void getArtistsByName_should_return_correct_artists() {
        List<Artist> artists = dao.getArtistsByName("f");
        assertNotNull(artists, "getArtistsByName returned a null list of artists");
        assertEquals(2, artists.size(), "getArtistsByName returned a list with the incorrect number of artists");
        assertEquals(ARTIST_2.getName(), artists.get(0).getName(), "getArtists returned a list in incorrect order");
        assertEquals(ARTIST_3.getName(), artists.get(1).getName(), "getArtists returned a list in incorrect order");
    }

    @Test
    public void getArtistsByName_with_empty_name_should_return_all_artists() {
        List<Artist> artists = dao.getArtistsByName("");
        assertNotNull(artists, "getArtistsByName returned a null list of artists");
        assertEquals(3, artists.size(), "getArtistsByName returned a list with the incorrect number of artists");
        assertEquals(ARTIST_1.getName(), artists.get(0).getName(), "getArtists returned a list in incorrect order");
        assertEquals(ARTIST_2.getName(), artists.get(1).getName(), "getArtists returned a list in incorrect order");
        assertEquals(ARTIST_3.getName(), artists.get(2).getName(), "getArtists returned a list in incorrect order");
    }

    @Test
    public void getArtistsByName_with_null_name_should_return_all_artists() {
        List<Artist> artists = dao.getArtistsByName(null);
        assertNotNull(artists, "getArtistsByName returned a null list of artists");
        assertEquals(3, artists.size(), "getArtistsByName returned a list with the incorrect number of artists");
        assertEquals(ARTIST_1.getName(), artists.get(0).getName(), "getArtists returned a list in incorrect order");
        assertEquals(ARTIST_2.getName(), artists.get(1).getName(), "getArtists returned a list in incorrect order");
        assertEquals(ARTIST_3.getName(), artists.get(2).getName(), "getArtists returned a list in incorrect order");
    }

    @Test
    public void getArtistsByTrackIds_with_valid_track_ids_should_return_correct_artists() {
        Map<Integer, List<Artist>> track_ids_and_artists = dao.getArtistsByTrackIds(new ArrayList<>(List.of(1,43,144)));
        assertNotNull(track_ids_and_artists, "getArtistsByTrackIds returned a null map");
        assertEquals(ARTIST_1.getName(), track_ids_and_artists.get(1).get(0).getName(), "getArtistsByTrackIds returned incorrect list of artists");
        assertEquals(ARTIST_2.getName(), track_ids_and_artists.get(43).get(0).getName(), "getArtistsByTrackIds returned incorrect list of artists");
        assertEquals(ARTIST_3.getName(), track_ids_and_artists.get(144).get(0).getName(), "getArtistsByTrackIds returned incorrect list of artists");
    }

    @Test
    public void getArtistsByTrackIds_with_invalid_track_ids_should_return_empty_map() {
        Map<Integer, List<Artist>> track_ids_and_artists = dao.getArtistsByTrackIds(new ArrayList<>(List.of(-1)));
        assertNotNull(track_ids_and_artists, "getArtistsByTrackIds with invalid track IDs returned a null map");
        assertEquals(new HashMap<Integer, List<Artist>>(), track_ids_and_artists, "getArtistsByTrackIds with invalid track IDs did not return empty map");
    }

    @Test
    public void getArtistsByTrackIds_with_empty_track_ids_list_should_return_empty_map() {
        Map<Integer, List<Artist>> track_ids_and_artists = dao.getArtistsByTrackIds(new ArrayList<>());
        assertNotNull(track_ids_and_artists, "getArtistsByTrackIds with empty track IDs list returned a null map");
        assertEquals(new HashMap<Integer, List<Artist>>(), track_ids_and_artists, "getArtistsByTrackIds with empty track IDs list did not return empty map");
    }

    @Test
    public void getArtistsByTrackIds_with_null_track_ids_list_should_return_empty_map() {
        Map<Integer, List<Artist>> track_ids_and_artists = dao.getArtistsByTrackIds(null);
        assertNotNull(track_ids_and_artists, "getArtistsByTrackIds with null track IDs list returned a null map");
        assertEquals(new HashMap<Integer, List<Artist>>(), track_ids_and_artists, "getArtistsByTrackIds with null track IDs list did not return empty map");
    }

    @Test
    public void getArtistsByTrackIds_with_null_track_ids_should_return_correct_map() {
        List<Integer> ints = new ArrayList<>();
        ints.add(null);
        ints.add(1);
        Map<Integer, List<Artist>> track_ids_and_artists = dao.getArtistsByTrackIds(ints);
        assertNotNull(track_ids_and_artists, "getArtistsByTrackIds with null track IDs returned a null map");
        assertEquals(1, track_ids_and_artists.size(), "getArtistsByTrackIds with null track IDs returned map of incorrect size");
        assertEquals(ARTIST_1.getName(), track_ids_and_artists.get(1).get(0).getName(), "getArtistsByTrackIds returned incorrect list of artists");
    }

    @Test
    public void createArtist_should_create_artist() {
        Artist newArtist = new Artist(4, "Jack Black", 1);

        Artist artist = dao.createArtist(newArtist);
        assertNotNull(artist, "Call to create should return non-null artist");

        Artist actualArtist = dao.getArtistById(artist.getId());
        assertNotNull(actualArtist, "Call to getArtistById after call to create should return non-null artist");

        newArtist.setId(actualArtist.getId());
        assertEquals(newArtist.getName(), actualArtist.getName());
    }

    @Test
    public void createArtist_with_no_name_should_throw_error() {
        try {
            dao.createArtist(new Artist(4, null, 1));
            fail("Expected createArtist() with null name to throw DaoException, but it didn't throw any exception");
        } catch (DaoException e) {
            // expected condition
        } catch (Exception e) {
            fail("Expected createArtist() with null name to throw DaoException, but threw a different exception");
        }
    }
}