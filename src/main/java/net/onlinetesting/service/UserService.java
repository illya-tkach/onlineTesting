package net.onlinetesting.service;

import net.onlinetesting.model.Role;
import net.onlinetesting.model.User;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface UserService {
    void save(User user) throws DataIntegrityViolationException;

    List<User> getAllUsers();

    User findById(String id);

    List<Role> findAllRole();

    void deleteUserById(String id);
}
