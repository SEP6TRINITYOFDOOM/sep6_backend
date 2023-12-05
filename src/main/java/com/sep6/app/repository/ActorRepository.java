package com.sep6.app.repository;

import com.sep6.app.model.Actor;
import com.sep6.app.model.Movie;

import java.util.List;

public interface ActorRepository extends CrudRepository<Actor, Integer>
{
    List<Actor> findByMovie(int movie_id);
}
