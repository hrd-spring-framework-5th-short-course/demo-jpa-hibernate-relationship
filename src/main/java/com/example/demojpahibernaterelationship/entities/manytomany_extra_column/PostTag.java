package com.example.demojpahibernaterelationship.entities.manytomany_extra_column;


import javax.persistence.*;
import java.sql.Timestamp;

// composite table
@Entity
public class PostTag {

    @EmbeddedId
    private PrimaryKey primaryKey;

    @ManyToOne
    @MapsId("firstKey")
    private Post post;

    @ManyToOne
    @MapsId("secondKey")
    private Tag tag;

    // extra column
    private Timestamp createdAt;


}

