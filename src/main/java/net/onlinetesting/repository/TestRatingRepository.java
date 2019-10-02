package net.onlinetesting.repository;

import net.onlinetesting.model.TestRating;
import net.onlinetesting.model.TestRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRatingRepository extends JpaRepository<TestRating, TestRatingKey> {
}
