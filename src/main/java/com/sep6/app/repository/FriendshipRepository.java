package com.sep6.app.repository;

import com.sep6.app.model.Friendship;
import com.sep6.app.model.FriendshipStatus;
import com.sep6.app.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends CrudRepository<Friendship, Integer> {

    List<Friendship> findByRequesterOrTargetAndStatus(User requester, User target, FriendshipStatus status);
    List<Friendship> findByRequester_IdAndStatus(Integer requesterId, FriendshipStatus status);
    Optional<Friendship> findByRequester_IdAndTarget_Id(Integer requesterId, Integer targetId);
    Optional<Friendship> findByRequester_IdOrTarget_Id(Integer requesterId, Integer targetId);




}
