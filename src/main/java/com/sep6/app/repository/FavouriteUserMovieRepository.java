package com.sep6.app.repository;

import com.sep6.app.model.FavouriteMovie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteUserMovieRepository extends CrudRepository<FavouriteMovie, Integer>{

    List<FavouriteMovie> findByUserId(Integer userId);

}
