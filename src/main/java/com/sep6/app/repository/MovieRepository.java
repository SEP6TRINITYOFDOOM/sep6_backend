package com.sep6.app.repository;

import com.sep6.app.model.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
}
