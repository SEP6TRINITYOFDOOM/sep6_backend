package com.sep6.app;

import com.sep6.app.service.DTO.ActorTMDB;
import com.sep6.app.service.DTO.MovieTMDB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ActorTMDBTest
{
    @Test
    public void testZeroValues() {
        ActorTMDB actor = new ActorTMDB();
        assertEquals(0, actor.getGender());
        assertEquals(0, actor.getId());
        assertNull(actor.getKnown_for_department());
        assertNull(actor.getName());
        assertNull(actor.getOriginal_name());
        assertEquals(0.0f, actor.getPopularity());
        assertNull(actor.getProfile_path());
        assertNull(actor.getKnown_for());
    }

    @Test
    public void testNegativeValues() {
        ActorTMDB actor = new ActorTMDB();
        // Simulate unexpected scenarios with negative values
        assertThrows(RuntimeException.class, () -> actor.setGender(-1), "Negative gender should throw an exception");

        assertThrows(RuntimeException.class, () -> actor.setId(-1), "Negative ID should throw an exception");

        assertThrows(RuntimeException.class, () -> actor.setPopularity(-1.0f), "Negative popularity should throw an exception");
    }

    @Test
    public void testNullValues() {
        ActorTMDB actor = new ActorTMDB();
        // Simulate unexpected scenarios with null values
        assertThrows(RuntimeException.class, () -> actor.setKnown_for_department(null), "Null department should throw an exception");

        assertThrows(RuntimeException.class, () -> actor.setName(null), "Null name should throw an exception");

        assertThrows(RuntimeException.class, () -> actor.setOriginal_name(null), "Null original name should throw an exception");

        assertThrows(RuntimeException.class, () -> actor.setProfile_path(null), "Null profile path should throw an exception");

        // Simulate an unexpected scenario where known_for is null
        assertThrows(RuntimeException.class, () -> actor.setKnown_for(null), "Null known_for should throw an exception");
    }

    @Test
    public void testEmptyValues() {
        ActorTMDB actor = new ActorTMDB();
        // Simulate unexpected scenarios with empty values
        assertThrows(RuntimeException.class, () -> actor.setKnown_for_department(""), "Empty department should throw an exception");

        assertThrows(RuntimeException.class, () -> actor.setName(""), "Empty name should throw an exception");

        assertThrows(RuntimeException.class, () -> actor.setOriginal_name(""), "Empty original name should throw an exception");
    }

    @Test
    public void testValidValues() {
        ActorTMDB actor = new ActorTMDB();
        // Set valid values and ensure they are correctly set
        actor.setGender(1);
        assertEquals(1, actor.getGender());

        actor.setId(123);
        assertEquals(123, actor.getId());

        actor.setKnown_for_department("Acting");
        assertEquals("Acting", actor.getKnown_for_department());

        actor.setName("John Doe");
        assertEquals("John Doe", actor.getName());

        actor.setOriginal_name("John Original Doe");
        assertEquals("John Original Doe", actor.getOriginal_name());

        actor.setPopularity(7.5f);
        assertEquals(7.5f, actor.getPopularity());

        actor.setProfile_path("/example/profile.jpg");
        assertEquals("/example/profile.jpg", actor.getProfile_path());

        MovieTMDB[] knownForMovies = {new MovieTMDB(), new MovieTMDB()};
        actor.setKnown_for(knownForMovies);
        assertArrayEquals(knownForMovies, actor.getKnown_for());
    }

}
