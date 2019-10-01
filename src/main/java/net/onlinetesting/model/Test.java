package net.onlinetesting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topicName", nullable = false)
    private String topicName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
    private Set<Question> questions;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private Set<TestRating> testRatings;

}
