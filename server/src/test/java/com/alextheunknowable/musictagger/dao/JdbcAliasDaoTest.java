package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Alias;
import com.alextheunknowable.musictagger.model.OriginType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcAliasDaoTest extends BaseDaoTest {
    private static final Alias ALIAS_1 = new Alias(1, 1, "OneShot Main Theme", 4);
    private static final Alias ALIAS_2 = new Alias(2, 129,"Undertale Final Boss", 4);
    private static final Alias ALIAS_3 = new Alias(3, 142,"Sans battle", 4);
    private static final Alias ALIAS_4 = new Alias(4, 142,"Sans theme", 4);
    private JdbcAliasDao dao;

    @BeforeEach
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcAliasDao(jdbcTemplate);
    }

    @Test
    public void getAliases_returns_correct_list_of_aliases() {
        List<Alias> aliases = dao.getAliases();

        assertNotNull(aliases, "getAliases returned a null list of aliases");
        assertEquals(4, aliases.size(), "getAliases returned a list with the incorrect number of aliases");
        assertEquals(ALIAS_1.getAlias(), aliases.get(0).getAlias(), "getAliases returned a list in incorrect order");
        assertEquals(ALIAS_2.getAlias(), aliases.get(1).getAlias(), "getAliases returned a list in incorrect order");
        assertEquals(ALIAS_3.getAlias(), aliases.get(2).getAlias(), "getAliases returned a list in incorrect order");
        assertEquals(ALIAS_4.getAlias(), aliases.get(3).getAlias(), "getAliases returned a list in incorrect order");
    }

    @Test
    public void getAliasById_with_valid_id_returns_correct_alias() {
        Alias actualAlias = dao.getAliasById(ALIAS_1.getId());
        assertEquals(ALIAS_1.getAlias(), actualAlias.getAlias(), "getAliasById with valid id did not return correct alias");
    }

    @Test
    public void getAliasById_with_invalid_id_returns_null() {
        Alias alias = dao.getAliasById(-1);
        assertNull(alias, "getAliasById with invalid aliasId did not return null alias");
    }

    @Test
    public void getAliasesByTrackIds_with_valid_ids_should_return_correct_aliases() {
        Map<Integer, List<Alias>> track_ids_and_aliases = dao.getAliasesByTrackIds(new ArrayList<>(List.of(1,129,142)));
        assertNotNull(track_ids_and_aliases, "getAliasesByTrackIds returned a null map");
        System.out.println(track_ids_and_aliases);
        assertEquals(ALIAS_1.getAlias(), track_ids_and_aliases.get(1).get(0).getAlias(), "getAliasesByTrackIds returned incorrect list of aliases");
        assertEquals(ALIAS_2.getAlias(), track_ids_and_aliases.get(129).get(0).getAlias(), "getAliasesByTrackIds returned incorrect list of aliases");
        assertEquals(ALIAS_3.getAlias(), track_ids_and_aliases.get(142).get(0).getAlias(), "getAliasesByTrackIds returned incorrect list of aliases");
        assertEquals(ALIAS_4.getAlias(), track_ids_and_aliases.get(142).get(1).getAlias(), "getAliasesByTrackIds returned incorrect list of aliases");
    }

    @Test
    public void getAliasesByTrackIds_with_invalid_ids_should_return_empty_map() {
        Map<Integer, List<Alias>> track_ids_and_aliases = dao.getAliasesByTrackIds(new ArrayList<>(List.of(-1)));
        assertNotNull(track_ids_and_aliases, "getAliasesByTrackIds with invalid track IDs returned a null map");
        assertEquals(new HashMap<Integer, List<Alias>>(), track_ids_and_aliases, "getAliasesByTrackIds with invalid track IDs did not return empty map");
    }

    @Test
    public void getAliasesByTrackIds_with_empty_ids_list_should_return_empty_map() {
        Map<Integer, List<Alias>> track_ids_and_aliases = dao.getAliasesByTrackIds(new ArrayList<>());
        assertNotNull(track_ids_and_aliases, "getAliasesByTrackIds with empty track IDs list returned a null map");
        assertEquals(new HashMap<Integer, List<Alias>>(), track_ids_and_aliases, "getAliasesByTrackIds with empty track IDs list did not return empty map");
    }

    @Test
    public void getAliasesByTrackIds_with_null_ids_list_should_return_empty_map() {
        Map<Integer, List<Alias>> track_ids_and_aliases = dao.getAliasesByTrackIds(null);
        assertNotNull(track_ids_and_aliases, "getAliasesByTrackIds with null track IDs list returned a null map");
        assertEquals(new HashMap<Integer, List<Alias>>(), track_ids_and_aliases, "getAliasesByTrackIds with null track IDs list did not return empty map");
    }

    @Test
    public void getAliasesByTrackIds_with_null_ids_should_return_correct_map() {
        List<Integer> ints = new ArrayList<>();
        ints.add(null);
        ints.add(1);
        Map<Integer, List<Alias>> track_ids_and_aliases = dao.getAliasesByTrackIds(ints);
        assertNotNull(track_ids_and_aliases, "getAliasesByTrackIds with null track IDs returned a null map");
        assertEquals(1, track_ids_and_aliases.size(), "getAliasesByTrackIds with null track IDs returned map of incorrect size");
        assertEquals(ALIAS_1.getAlias(), track_ids_and_aliases.get(1).get(0).getAlias(), "getAliasesByTrackIds returned incorrect list of aliases");
    }

    @Test
    public void createAlias_should_create_alias() {
        Alias newAlias = new Alias(999, 1, "oneshot theme", 4);

        Alias alias = dao.createAlias(newAlias);
        assertNotNull(alias, "Call to create should return non-null alias");

        Alias actualAlias = dao.getAliasById(alias.getId());
        assertNotNull(actualAlias, "Call to getAliasById after call to create should return non-null alias");

        newAlias.setId(actualAlias.getId());
        assertEquals(newAlias.getAlias(), actualAlias.getAlias());
    }

    @Test
    public void createAlias_with_no_url_should_throw_error() {
        try {
            dao.createAlias(new Alias(999, 1, null, 4));
            fail("Expected createAlias() with null name to throw DaoException, but it didn't throw any exception");
        } catch (DaoException e) {
            // expected condition
        } catch (Exception e) {
            fail("Expected createAlias() with null name to throw DaoException, but threw a different exception");
        }
    }
}