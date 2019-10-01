package net.onlinetesting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "definition", nullable = false)
    private String definition;

    @Enumerated(EnumType.STRING)
    @Column(name = "questionType", nullable = false)
    private QuestionType questionType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch=FetchType.EAGER)
    private List<Answer> answers;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

}
