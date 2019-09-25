package net.onlinetesting.repository;

import net.onlinetesting.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(nativeQuery=true, value="SELECT * FROM question where test_id = :testId ORDER BY rand() LIMIT 10")
    List<Question> getTenRandomQuestions (@Param("testId") long testId);

}
