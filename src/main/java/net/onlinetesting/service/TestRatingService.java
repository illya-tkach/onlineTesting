package net.onlinetesting.service;

import net.onlinetesting.model.Test;
import net.onlinetesting.model.TestRating;
import net.onlinetesting.model.TestRatingKey;
import net.onlinetesting.model.User;

import java.util.Optional;

public interface TestRatingService {
    void save(TestRating testRating);
    void save(User user, Test test, int points);
    Optional<TestRating> getRatingByKey(TestRatingKey key);
}
