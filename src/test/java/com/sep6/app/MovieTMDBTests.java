package com.sep6.app;

import com.sep6.app.service.DTO.MovieTMDB;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieTMDBTests
{
    @Test
    public void testZeroValues() {
        MovieTMDB movie = new MovieTMDB();
        assertFalse(movie.isAdult());
        assertNull(movie.getBackdrop_path());
        assertNull(movie.getGenre_ids());
        assertEquals(0, movie.getId());
        assertNull(movie.getOriginal_language());
        assertNull(movie.getOriginal_title());
        assertNull(movie.getOverview());
        assertEquals(0.0f, movie.getPopularity());
        assertNull(movie.getPoster_path());
        assertNull(movie.getRelease_date());
        assertNull(movie.getTitle());
        assertFalse(movie.isVideo());
        assertEquals(0.0f, movie.getVote_average());
        assertEquals(0, movie.getVote_count());
    }

    @Test
    public void testOneValue() {
        MovieTMDB movie = new MovieTMDB();
        movie.setAdult(true);
        assertTrue(movie.isAdult());
    }

    @Test
    public void testManyValues() {
        MovieTMDB movie = new MovieTMDB();
        movie.setBackdrop_path("/example/backdrop.jpg");
        movie.setGenre_ids(new int[]{1, 2, 3});
        movie.setId(123);
        movie.setOriginal_language("en");
        movie.setOriginal_title("Test Movie");
        movie.setOverview("This is a test movie");
        movie.setPopularity(7.5f);
        movie.setPoster_path("/example/poster.jpg");
        movie.setRelease_date("2023-01-01");
        movie.setTitle("Test Movie");
        movie.setVideo(true);
        movie.setVote_average(8.0f);
        movie.setVote_count(1000);

        assertEquals("/example/backdrop.jpg", movie.getBackdrop_path());
        assertArrayEquals(new int[]{1, 2, 3}, movie.getGenre_ids());
        assertEquals(123, movie.getId());
        assertEquals("en", movie.getOriginal_language());
        assertEquals("Test Movie", movie.getOriginal_title());
        assertEquals("This is a test movie", movie.getOverview());
        assertEquals(7.5f, movie.getPopularity());
        assertEquals("/example/poster.jpg", movie.getPoster_path());
        assertEquals("2023-01-01", movie.getRelease_date());
        assertEquals("Test Movie", movie.getTitle());
        assertTrue(movie.isVideo());
        assertEquals(8.0f, movie.getVote_average());
        assertEquals(1000, movie.getVote_count());
    }

    @Test
    public void testBoundaryValues() {
        MovieTMDB movie = new MovieTMDB();
        // For simplicity, let's assume that vote_count has an upper limit of 100000
        movie.setVote_count(100001);
        assertEquals(100000, movie.getVote_count());
    }

    @Test
    public void testSimpleScenarios() {
        MovieTMDB movie = new MovieTMDB();
        MovieTMDB anotherMovie = new MovieTMDB();

        // Two different instances should not be equal
        assertNotEquals(movie, anotherMovie);

        // Setting the same values should make the instances equal
        anotherMovie.setTitle("Test Movie");
        assertEquals(movie, anotherMovie);
    }
}
