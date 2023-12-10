package com.sep6.app.repository;

import com.sep6.app.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>
{
    List<Movie> findByTitle(String title);
    //List<Movie> findMovieByGenre_id(int genre_id);

}
