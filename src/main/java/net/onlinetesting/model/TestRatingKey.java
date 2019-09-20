package net.onlinetesting.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TestRatingKey implements Serializable {


    @Column(name = "user_id")
    private Long userId;


    @Column(name = "test_id")
    private Long testId;
}
