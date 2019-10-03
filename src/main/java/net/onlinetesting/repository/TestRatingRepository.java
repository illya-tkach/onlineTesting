package net.onlinetesting.repository;

import net.onlinetesting.model.TestRating;
import net.onlinetesting.model.TestRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRatingRepository extends JpaRepository<TestRating, TestRatingKey> {
    List<TestRating> findAllByOrderByUser_emailAsc();
}
