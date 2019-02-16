package com.example.demojpahibernaterelationship.entities.manytomany_extra_column;

        import javax.persistence.Embeddable;
        import java.io.Serializable;

@Embeddable
public class PrimaryKey implements Serializable {

    private Integer firstKey;
    private Integer secondKey;

}
