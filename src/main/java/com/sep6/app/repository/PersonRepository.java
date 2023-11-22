package com.sep6.app.repository;

import com.sep6.app.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository <Person, Integer>{
}
