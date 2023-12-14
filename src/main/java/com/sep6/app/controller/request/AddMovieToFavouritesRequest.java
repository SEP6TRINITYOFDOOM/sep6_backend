package com.sep6.app.controller.request;

public record AddMovieToFavouritesRequest(int userId, int movieId, int position) {
}
