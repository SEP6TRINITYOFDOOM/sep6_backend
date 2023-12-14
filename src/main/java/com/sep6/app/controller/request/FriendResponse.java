package com.sep6.app.controller.request;

public record FriendResponse(String status, int id, UserResponse user, int requesterId) {
}
