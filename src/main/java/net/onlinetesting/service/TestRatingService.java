package net.onlinetesting.service;

import net.onlinetesting.model.Test;
import net.onlinetesting.model.TestRating;
import net.onlinetesting.model.TestRatingKey;
import net.onlinetesting.model.User;

public interface TestRatingService {
    void save(TestRating testRating);
    TestRating getRatingByKey(TestRatingKey key);
}
