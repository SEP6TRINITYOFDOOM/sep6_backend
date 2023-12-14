package com.sep6.app.controller;

import com.sep6.app.controller.request.FriendResponse;
import com.sep6.app.controller.request.FriendshipRequest;
import com.sep6.app.controller.request.UserResponse;
import com.sep6.app.model.Friendship;
import com.sep6.app.model.FriendshipStatus;
import com.sep6.app.model.User;
import com.sep6.app.repository.FriendshipRepository;
import com.sep6.app.repository.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/friendships")
public class FriendshipController {

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    public FriendshipController(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/request")
    public ResponseEntity<String> sendFriendshipRequest(
            @RequestBody FriendshipRequest request
    ) {
        Optional<User> requesterOpt = userRepository.findById(request.requester());
        Optional<User> targetOpt = userRepository.findById(request.target());

        if (requesterOpt.isPresent() && targetOpt.isPresent()) {
            Friendship friendship = new Friendship(requesterOpt.get(), targetOpt.get(), FriendshipStatus.PENDING);
            friendshipRepository.save(friendship);
            return ResponseEntity.ok("Friendship request sent successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/accept")
    public ResponseEntity<String> acceptFriendshipRequest(
            @RequestParam Integer friendshipId,
            @RequestParam Integer accepterId
    ) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new FriendshipNotFoundException(friendshipId));

        if (friendship.getRequester().getId().equals(accepterId)) {
            return ResponseEntity.status(401).body("You cannot accept requests you have sent!");
        }
        if (!friendship.getTarget().getId().equals(accepterId)) {
            return ResponseEntity.status(401).body("You do not have a role to accept this friendship!");
        }

        friendship.setStatus(FriendshipStatus.ACCEPTED);
        friendshipRepository.save(friendship);

        return ResponseEntity.ok("Friendship request accepted successfully.");
    }

    @PutMapping("/block")
    public ResponseEntity<String> blockUser(
            @RequestParam Integer blockerId,
            @RequestParam Integer targetId
    ) {

        Optional<Friendship> friendshipOpt = Optional.ofNullable(
                friendshipRepository.findByRequester_IdAndTarget_Id(blockerId, targetId)
                        .orElse(friendshipRepository.findByRequester_IdAndTarget_Id(targetId, blockerId)
                                .orElse(null)));


        friendshipOpt.ifPresent(friendship -> friendshipRepository.deleteById(friendship.getId()));
        friendshipRepository.save(new Friendship(new User(blockerId), new User(targetId), FriendshipStatus.BLOCKED));

        return ResponseEntity.ok("Friendship blocked successfully.");
    }

    @DeleteMapping("/reject")
    public ResponseEntity<String> removeFriendship(
            @RequestParam Integer friendshipId,
            @RequestParam Integer rejecterId
    ) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new FriendshipNotFoundException(friendshipId));

        if (friendship.getStatus().equals(FriendshipStatus.BLOCKED) &&
                (friendship.getTarget().getId().equals(rejecterId) || !friendship.getRequester().getId().equals(rejecterId))) {
            return ResponseEntity.status(401).body("You cannot unblock yourself!");
        }

        if (!friendship.getRequester().getId().equals(rejecterId) && !friendship.getTarget().getId().equals(rejecterId)) {
            return ResponseEntity.status(401).body("You do not have a role to reject this friendship!");
        }
        friendshipRepository.deleteById(friendship.getId());

        return ResponseEntity.ok("Friendship removed successfully.");
    }


    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<FriendResponse>> getFriendList(@PathVariable Integer userId) {
        Optional<User> requesterOpt = userRepository.findById(userId);

        return requesterOpt.map(user ->
                        ResponseEntity.ok(getFriendshipsByStatus(user, FriendshipStatus.ACCEPTED)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/pending")
    public ResponseEntity<List<FriendResponse>> getPending(@PathVariable Integer userId) {
        Optional<User> requesterOpt = userRepository.findById(userId);

        return requesterOpt.map(user ->
                        ResponseEntity.ok(getFriendshipsByStatus(user, FriendshipStatus.PENDING)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/blocked")
    public ResponseEntity<List<FriendResponse>> getBlocked(@PathVariable Integer userId) {
        List<Friendship> requesterFriendships = friendshipRepository.findByRequester_IdAndStatus(userId, FriendshipStatus.BLOCKED);

        return ResponseEntity.ok(requesterFriendships.stream().filter(friendship -> friendship.getStatus().equals(FriendshipStatus.BLOCKED)).map(friendship ->
                new FriendResponse(FriendshipStatus.BLOCKED.toString(), friendship.getId(),
                        new UserResponse(friendship.getTarget().getUsername(), friendship.getTarget().getId()),
                        friendship.getRequester().getId())).toList());
    }

    private List<FriendResponse> getFriendshipsByStatus(User user, FriendshipStatus status) {

        List<Friendship> requesterFriendships = friendshipRepository.findByRequesterOrTargetAndStatus(user, user, status);
        return requesterFriendships.stream()
                .filter(friendship -> friendship.getStatus().equals(status))
                .map(friendship -> new FriendResponse(status.toString(), friendship.getId(), friendship.getRequester().equals(user) ?
                        new UserResponse(friendship.getTarget().getUsername(), friendship.getTarget().getId()) :
                        new UserResponse(friendship.getRequester().getUsername(), friendship.getRequester().getId()),
                        friendship.getRequester().getId())).toList();

    }
}
