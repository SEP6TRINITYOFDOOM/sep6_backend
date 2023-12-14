package com.sep6.app;
import com.sep6.app.service.DTO.MovieTMDB;
import com.sep6.app.service.DTO.MoviesTMDB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class MoviesTMDBTest
{
    @Test
    public void testZeroPage() {
        MoviesTMDB movies = new MoviesTMDB();
        assertEquals(0, movies.getPage());
    }

    @Test
    public void testOneTotalResults() {
        MoviesTMDB movies = new MoviesTMDB();
        movies.setTotal_results(50);
        assertEquals(50, movies.getTotal_results());
    }

    @Test
    public void testManyResults() {
        MoviesTMDB movies = new MoviesTMDB();
        MovieTMDB[] movieResults = {new MovieTMDB(), new MovieTMDB()};
        movies.setResults(movieResults);
        assertArrayEquals(movieResults, movies.getResults());
    }

    @Test
    public void testBoundaryTotalPages() {
        MoviesTMDB movies = new MoviesTMDB();
        movies.setTotal_pages(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, movies.getTotal_pages());
    }

    @Test
    public void testInterfaceDefinitionPage() {
        MoviesTMDB movies = new MoviesTMDB();
        assertThrows(IllegalArgumentException.class, () -> movies.setPage(-1));
    }

    @Test
    public void testExceptionalBehaviorNullResults() {
        MoviesTMDB movies = new MoviesTMDB();
        assertThrows(NullPointerException.class, () -> movies.setResults(null));
    }

    @Test
    public void testSimpleScenariosTotalResults() {
        MoviesTMDB movies = new MoviesTMDB();
        movies.setTotal_results(100);
        assertEquals(100, movies.getTotal_results());
    }
}
