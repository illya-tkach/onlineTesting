package net.onlinetesting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "testRating")
public class TestRating implements Serializable {

    @EmbeddedId
    TestRatingKey testRatingKey;

    @MapsId("userID")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @MapsId("testID")
    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test test;

    @Column
    private int rating;

    public TestRating(User user, Test test, int rating) {
        this.user = user;
        this.test = test;
        this.rating = rating;
    }
}
