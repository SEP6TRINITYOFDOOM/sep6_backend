package com.sep6.app.repository;

import com.sep6.app.model.Rating;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer>{
}
