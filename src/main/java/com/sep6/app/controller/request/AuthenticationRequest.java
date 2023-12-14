package com.sep6.app.controller.request;

public record AuthenticationRequest(String username, String password, String email) {
}
