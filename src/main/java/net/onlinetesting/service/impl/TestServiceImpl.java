package net.onlinetesting.service.impl;

import net.onlinetesting.model.Test;
import net.onlinetesting.repository.TestRepository;
import net.onlinetesting.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestRepository testRepository;


    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @Override
    public Test getById() {
        return null;
    }
}
