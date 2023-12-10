package com.sep6.app.repository;

import com.sep6.app.model.Director;
import com.sep6.app.model.DirectorId;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface DirectorRepository extends CrudRepository<Director, DirectorId> {
}
