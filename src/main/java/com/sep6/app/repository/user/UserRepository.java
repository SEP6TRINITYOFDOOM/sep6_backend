package com.sep6.app.repository.user;

import com.sep6.app.model.User;
import com.sep6.app.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    List<User> findByUsernameLike(String username);
}
