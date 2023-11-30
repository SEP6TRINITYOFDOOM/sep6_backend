package com.sep6.app.model;

import jakarta.persistence.*;
import org.joda.time.DateTime;

import static jakarta.persistence.GenerationType.AUTO;

@Entity()
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

//    @ForeignKey(name = )
    private Integer userId;

    //    @ForeignKey(name = )
    private Integer movieId;

    private Integer ratingValue;

    private DateTime createdOn;
    private DateTime updatedOn;

    public Rating(){}

}
