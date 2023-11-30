package com.sep6.app.model;

import java.io.Serializable;

public class StarId implements Serializable {
    private Integer movieId;
    private Integer personId;

    public StarId(Integer movieId, Integer personId) {
        this.movieId = movieId;
        this.personId = personId;
    }

    public StarId(){}

    public static StarId of(Integer movieId, Integer personId){
        return new StarId(movieId, personId);
    }
}
