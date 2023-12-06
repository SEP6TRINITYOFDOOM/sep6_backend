package com.sep6.app;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrendingActor {

    public TrendingActor(){

    }
    public TrendingActor(Integer id, String name, String path){
        this.id = id;
        this.name = name;
        this.path = path;
    }

    private Integer id;
    private String name;
    private String path;

}
