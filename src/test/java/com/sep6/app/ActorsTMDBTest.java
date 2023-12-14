package com.sep6.app;
import com.sep6.app.service.DTO.ActorTMDB;
import com.sep6.app.service.DTO.ActorsTMDB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ActorsTMDBTest
{
    @Test
    public void testZeroPage() {
        ActorsTMDB actors = new ActorsTMDB();
        assertEquals(0, actors.getPage());
    }

    @Test
    public void testOneTotalResults() {
        ActorsTMDB actors = new ActorsTMDB();
        actors.setTotal_results(50);
        assertEquals(50, actors.getTotal_results());
    }

    @Test
    public void testManyResults() {
        ActorsTMDB actors = new ActorsTMDB();
        ActorTMDB[] actorResults = {new ActorTMDB(), new ActorTMDB()};
        actors.setResults(actorResults);
        assertArrayEquals(actorResults, actors.getResults());
    }

    @Test
    public void testBoundaryTotalPages() {
        ActorsTMDB actors = new ActorsTMDB();
        actors.setTotal_pages(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, actors.getTotal_pages());
    }

    @Test
    public void testInterfaceDefinitionPage() {
        ActorsTMDB actors = new ActorsTMDB();
        assertThrows(IllegalArgumentException.class, () -> actors.setPage(-1));
    }

    @Test
    public void testExceptionalBehaviorNullResults() {
        ActorsTMDB actors = new ActorsTMDB();
        assertThrows(NullPointerException.class, () -> actors.setResults(null));
    }

    @Test
    public void testSimpleScenariosTotalResults() {
        ActorsTMDB actors = new ActorsTMDB();
        actors.setTotal_results(100);
        assertEquals(100, actors.getTotal_results());
    }
}
