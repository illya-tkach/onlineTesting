package net.onlinetesting.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "testRating")
public class TestRating implements Serializable {

    @EmbeddedId
    TestRatingKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("test_id")
    @JoinColumn(name = "test_id")
    Test test;

    @Column
    private int rating;

    public TestRating(User user, Test test, int rating) {
        this.user = user;
        this.test = test;
        this.rating = rating;
    }
}
