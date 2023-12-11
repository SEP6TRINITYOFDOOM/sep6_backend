DROP SCHEMA IF EXISTS sep6 CASCADE;

CREATE SCHEMA IF NOT EXISTS sep6;
SET SCHEMA 'sep6';

DROP TABLE IF EXISTS Person CASCADE;
CREATE TABLE IF NOT EXISTS Person
(
    id              int             UNIQUE PRIMARY KEY,
    person_name     varchar(100)    NOT NULL,
    birth           DATE            NOT NULL,
    created_on      DATE            NOT NULL,
    updated_on      DATE            NOT NULL
);

DROP TABLE IF EXISTS Genre CASCADE;
CREATE TABLE IF NOT EXISTS Genre
(
    id              int             UNIQUE PRIMARY KEY,
    name            varchar(100)    UNIQUE NOT NULL,
    created_on      DATE            NOT NULL,
    updated_on      DATE            NOT NULL
);

DROP TABLE IF EXISTS Movie CASCADE;
CREATE TABLE IF NOT EXISTS Movie
(
    id              int UNIQUE      PRIMARY KEY,
    title           varchar(100)    NOT NULL,
    release_year    int             NOT NULL,
    created_on      DATE            NOT NULL,
    updated_on      DATE            NOT NULL,
    genre_id        int             NOT NULL,

    FOREIGN KEY (genre_id) REFERENCES Genre (id) ON DELETE CASCADE
);


DROP TABLE IF EXISTS Director CASCADE;
CREATE TABLE IF NOT EXISTS Director
(
    person_id       int             NOT NULL,
    movie_id        int             NOT NULL,
    birth           DATE            NOT NULL,
    created_on      DATE            NOT NULL,
    updated_on      DATE            NOT NULL,

    FOREIGN KEY (person_id) REFERENCES Person (id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES Movie (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Actor CASCADE;
CREATE TABLE IF NOT EXISTS Actor
(
    person_id       int             NOT NULL,
    movie_id        int             NOT NULL,
    birth           DATE            NOT NULL,
    created_on      DATE            NOT NULL,
    updated_on      DATE            NOT NULL,

    FOREIGN KEY (person_id) REFERENCES Person (id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES Movie (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Stars CASCADE;
CREATE TABLE IF NOT EXISTS Stars
(
    person_id       int             NOT NULL,
    movie_id        int             NOT NULL,
    birth           DATE            NOT NULL,
    created_on      DATE            NOT NULL,
    updated_on      DATE            NOT NULL,

    FOREIGN KEY (person_id) REFERENCES Person (id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES Movie (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE IF NOT EXISTS Users
(
    id              int UNIQUE      PRIMARY KEY,
    email           varchar(100)    NOT NULL,
    username        varchar(100)    NOT NULL,
    password        varchar(100)    NOT NULL,
    updated_on      DATE            NOT NULL
);

DROP TABLE IF EXISTS WatchList CASCADE;
CREATE TABLE IF NOT EXISTS WatchList
(
    id              int             NOT NULL,
    user_id         int             NOT NULL,
    movie_id        int             NOT NULL,
    created_on      DATE            NOT NULL,
    updated_on      DATE            NOT NULL,

    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES Movie (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Rating CASCADE;
CREATE TABLE IF NOT EXISTS Rating
(
    id              int             NOT NULL,
    user_id         int             NOT NULL,
    movie_id        int             NOT NULL,
    rating          int             NOT NULL,
    created_on      DATE            NOT NULL,
    updated_on      DATE            NOT NULL,

    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES Movie (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS ActorMovieRating CASCADE;
CREATE TABLE IF NOT EXISTS ActorMovieRating
(
    person_id       int             NOT NULL,
    movie_id        int             NOT NULL,
    user_id         int             NOT NULL,
    rating          int             NOT NULL,
    created_on      DATE            NOT NULL,
    updated_on      DATE            NOT NULL,

    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES Movie (id) ON DELETE CASCADE,
    FOREIGN KEY (person_id) REFERENCES Person (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS FavouriteUserMovie CASCADE;
CREATE TABLE IF NOT EXISTS FavouriteUserMovie
(
    id              int             NOT NULL,
    user_id        int             NOT NULL,
    movie_id         int             NOT NULL,
    position        int             NOT NULL,
    created_on      DATE            NOT NULL,
    updated_on      DATE            NOT NULL,

    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES Movie (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS UserComments CASCADE;
CREATE TABLE IF NOT EXISTS UserComments
(
    id              int             UNIQUE PRIMARY KEY ,
    user_id         int             NOT NULL,
    movie_id        int             NOT NULL,
    content         varchar(300)    NOT NULL,
    replied_to      int             NULL,
    created_on      DATE            NOT NULL,
    updated_on      DATE            NOT NULL,

    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES Movie (id) ON DELETE CASCADE,
    FOREIGN KEY (replied_to) REFERENCES UserComments (id) ON DELETE CASCADE
);