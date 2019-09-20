package net.onlinetesting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Data
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
    private Set<Question> questions;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private Set<TestRating> testRatings;

}
