package com.sep6.app.controller.request;

public record EditUserInfoRequest(int id, String email, String password, String username) {
}
