package net.onlinetesting.service;

import net.onlinetesting.model.Test;

import java.util.List;

public interface TestService {
    List<Test> getAllTests();

    Test getById(long id);
}
