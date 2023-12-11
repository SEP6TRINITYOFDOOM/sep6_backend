package com.sep6.app;

public class ActorTMDB {

    public ActorTMDB(){

    }

    int gender;
    int id;
    String known_for_department;
    String name;
    String original_name;
    float popularity;
    String profile_path;
    MovieTMDB[] known_for;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public MovieTMDB[] getKnown_for() {
        return known_for;
    }

    public void setKnown_for(MovieTMDB[] known_for) {
        this.known_for = known_for;
    }
}
