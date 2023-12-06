package com.sep6.app;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Actor {

    public Actor(){

    }

    int gender;
    int id;
    String known_for_department;
    String name;
    String original_name;
    float popularity;
    String profile_path;
    Movie[] known_for;
}
