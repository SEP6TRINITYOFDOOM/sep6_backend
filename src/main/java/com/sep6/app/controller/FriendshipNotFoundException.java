package com.sep6.app.controller;

public class FriendshipNotFoundException extends RuntimeException {

    public FriendshipNotFoundException(Integer friendshipId) {
        super("Friendship not found with id: " + friendshipId);
    }
}
