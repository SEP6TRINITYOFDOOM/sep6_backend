package com.sep6.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.AUTO;

@Entity()
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer movie_id;

//    @ForeignKey(name = )
    //private Integer userId;

    //    @ForeignKey(name = )
    //private Integer movie_id;

    private Double rating;

    private Integer votes;



    //private Timestamp createdOn;
    //private Timestamp updatedOn;

    public Rating(){}

}
