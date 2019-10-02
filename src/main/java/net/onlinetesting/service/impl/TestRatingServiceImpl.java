package net.onlinetesting.service.impl;

import net.onlinetesting.model.Test;
import net.onlinetesting.model.TestRating;
import net.onlinetesting.model.TestRatingKey;
import net.onlinetesting.model.User;
import net.onlinetesting.repository.TestRatingRepository;
import net.onlinetesting.service.TestRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TestRatingServiceImpl implements TestRatingService {

    @Autowired
    TestRatingRepository testRatingRepository;

    @Override
    public void save(TestRating testRating) {
        testRatingRepository.save(testRating);
    }

    @Override
    public void save(User user, Test test, int points) {
        TestRatingKey key = new TestRatingKey(user.getId(), test.getId());

        getRatingByKey(key).ifPresentOrElse(value -> {value.setRating(points); save(value);},
                () -> save(new TestRating(key, user, test, points))
        );
    }

    @Override
    public Optional<TestRating> getRatingByKey(TestRatingKey key) {
        return testRatingRepository.findById(key);
    }
}
