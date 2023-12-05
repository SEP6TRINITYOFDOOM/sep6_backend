package com.sep6.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "actor")
public class Actor
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int movie_id;
    private int person_id;
    private LocalDate created_on;
    private LocalDate updated_on;

    public Actor(int movie_id, int person_id)
    {
        this.movie_id = movie_id;
        this.person_id = person_id;
        this.created_on = LocalDate.now();
        this.updated_on = LocalDate.now();
    }

    public Actor()
    {

    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public int getMovie_id()
    {
        return movie_id;
    }

    public void setMovie_id(int movie_id)
    {
        this.movie_id = movie_id;
    }

    public int getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(int person_id)
    {
        this.person_id = person_id;
    }

    public LocalDate getCreated_on()
    {
        return created_on;
    }

    public void setCreated_on(LocalDate created_on)
    {
        this.created_on = created_on;
    }

    public LocalDate getUpdated_on()
    {
        return updated_on;
    }

    public void setUpdated_on(LocalDate updated_on)
    {
        this.updated_on = updated_on;
    }
}
