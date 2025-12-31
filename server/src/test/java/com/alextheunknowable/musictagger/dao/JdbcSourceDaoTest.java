package com.alextheunknowable.musictagger.dao;

import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.Source;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcSourceDaoTest extends BaseDaoTest {
    private static final Source SOURCE_1 = new Source(1, "OneShot Original Soundtrack", "https://i.ytimg.com/vi/-wtiB7oXKsw/maxresdefault.jpg", 4);
    private static final Source SOURCE_2 = new Source(2, "UNDERTALE Soundtrack", "https://i.ytimg.com/vi/0AQMAth2gio/maxresdefault.jpg", 4);
    private static final Source SOURCE_3 = new Source(3, "Paper Lily OST", "https://i.ytimg.com/vi/IgeXLLAwNcs/maxresdefault.jpg", 1);
    private JdbcSourceDao dao;

    @BeforeEach
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcSourceDao(jdbcTemplate);
    }

    @Test
    public void getSources_returns_correct_list_of_sources() {
        List<Source> sources = dao.getSources();

        assertNotNull(sources, "getSources returned a null list of sources");
        assertEquals(3, sources.size(), "getSources returned a list with the incorrect number of sources");
        assertEquals(SOURCE_1.getName(), sources.get(0).getName(), "getSources returned a list in incorrect order");
        assertEquals(SOURCE_2.getName(), sources.get(1).getName(), "getSources returned a list in incorrect order");
        assertEquals(SOURCE_3.getName(), sources.get(2).getName(), "getSources returned a list in incorrect order");
    }

    @Test
    public void getSourceById_with_valid_id_returns_correct_source() {
        Source actualSource = dao.getSourceById(SOURCE_1.getId());
        assertEquals(SOURCE_1.getName(), actualSource.getName(), "getSourceById with valid id did not return correct source");
    }

    @Test
    public void getSourceById_with_invalid_id_returns_null() {
        Source source = dao.getSourceById(-1);
        assertNull(source, "getSourceById with invalid sourceId did not return null source");
    }

    @Test
    public void getSourcesByName_should_return_correct_sources() {
        List<Source> sources = dao.getSourcesByName("n");
        assertNotNull(sources, "getSourcesByName returned a null list of sources");
        assertEquals(2, sources.size(), "getSourcesByName returned a list with the incorrect number of sources");
        assertEquals(SOURCE_1.getName(), sources.get(0).getName(), "getSources returned a list in incorrect order");
        assertEquals(SOURCE_2.getName(), sources.get(1).getName(), "getSources returned a list in incorrect order");
    }

    @Test
    public void getSourcesByName_with_empty_name_should_return_all_sources() {
        List<Source> sources = dao.getSourcesByName("");
        assertNotNull(sources, "getSourcesByName returned a null list of sources");
        assertEquals(3, sources.size(), "getSourcesByName returned a list with the incorrect number of sources");
        assertEquals(SOURCE_1.getName(), sources.get(0).getName(), "getSources returned a list in incorrect order");
        assertEquals(SOURCE_2.getName(), sources.get(1).getName(), "getSources returned a list in incorrect order");
        assertEquals(SOURCE_3.getName(), sources.get(2).getName(), "getSources returned a list in incorrect order");
    }

    @Test
    public void getSourcesByName_with_null_name_should_return_all_sources() {
        List<Source> sources = dao.getSourcesByName(null);
        assertNotNull(sources, "getSourcesByName returned a null list of sources");
        assertEquals(3, sources.size(), "getSourcesByName returned a list with the incorrect number of sources");
        assertEquals(SOURCE_1.getName(), sources.get(0).getName(), "getSources returned a list in incorrect order");
        assertEquals(SOURCE_2.getName(), sources.get(1).getName(), "getSources returned a list in incorrect order");
        assertEquals(SOURCE_3.getName(), sources.get(2).getName(), "getSources returned a list in incorrect order");
    }

    @Test
    public void createSource_should_create_source() {
        Source newSource = new Source("The Jack Black Album", "https://i.ytimg.com/vi/DkDP6-yGN-M/maxresdefault.jpg", 2);

        Source source = dao.createSource(newSource);
        assertNotNull(source, "Call to create should return non-null source");

        Source actualSource = dao.getSourceById(source.getId());
        assertNotNull(actualSource, "Call to getSourceById after call to create should return non-null source");

        newSource.setId(actualSource.getId());
        assertEquals(newSource.getName(), actualSource.getName());
    }

    @Test
    public void createSource_with_no_name_should_throw_error() {
        try {
            dao.createSource(new Source(null, "https://i.ytimg.com/vi/DkDP6-yGN-M/maxresdefault.jpg", 2));
            fail("Expected createSource() with null name to throw DaoException, but it didn't throw any exception");
        } catch (DaoException e) {
            // expected condition
        } catch (Exception e) {
            fail("Expected createSource() with null name to throw DaoException, but threw a different exception");
        }
    }
}