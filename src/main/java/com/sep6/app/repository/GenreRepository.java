package com.sep6.app.repository;

import com.sep6.app.model.Genre;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer>
{

}
