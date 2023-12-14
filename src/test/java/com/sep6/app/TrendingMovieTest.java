package com.sep6.app;

import com.sep6.app.service.DTO.TrendingMovie;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrendingMovieTest
{
    @Test
    public void testId() {
        TrendingMovie trendingMovie = new TrendingMovie(1, "Movie Title", 2023, 1, "poster_path");
        assertEquals(1, trendingMovie.getId());
    }

    @Test
    public void testTitle() {
        TrendingMovie trendingMovie = new TrendingMovie(1, "Movie Title", 2023, 1, "poster_path");
        assertEquals("Movie Title", trendingMovie.getTitle());
    }

    @Test
    public void testYear() {
        TrendingMovie trendingMovie = new TrendingMovie(1, "Movie Title", 2023, 1, "poster_path");
        assertEquals(2023, trendingMovie.getYear());
    }

    @Test
    public void testCreatedOn() {
        TrendingMovie trendingMovie = new TrendingMovie(1, "Movie Title", 2023, 1, "poster_path");
        assertEquals(LocalDate.now(), trendingMovie.getCreated_on());
    }

    @Test
    public void testUpdatedOn() {
        TrendingMovie trendingMovie = new TrendingMovie(1, "Movie Title", 2023, 1, "poster_path");
        assertEquals(LocalDate.now(), trendingMovie.getUpdated_on());
    }

    @Test
    public void testGenreId() {
        TrendingMovie trendingMovie = new TrendingMovie(1, "Movie Title", 2023, 1, "poster_path");
        assertEquals(1, trendingMovie.getGenre_id());
    }

    @Test
    public void testPath() {
        TrendingMovie trendingMovie = new TrendingMovie(1, "Movie Title", 2023, 1, "poster_path");
        assertEquals("poster_path", trendingMovie.getPath());
    }
}
