package com.sep6.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
@Entity
@Table(name = "genre")
public class Genre
{
    @Id
    private int id;
    private String name;
    private LocalDate created_on;
    private LocalDate updated_on;

    public Genre(int id, String name)
    {
        this.id = id;
        this.name = name;
        this.created_on = LocalDate.now();
        this.updated_on = LocalDate.now();
    }

    public Genre()
    {

    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public LocalDate getCreated_on()
    {
        return created_on;
    }

    public LocalDate getUpdated_on()
    {
        return updated_on;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCreated_on(LocalDate created_on)
    {
        this.created_on = created_on;
    }

    public void setUpdated_on(LocalDate updated_on)
    {
        this.updated_on = updated_on;
    }
}
