package net.onlinetesting.service.impl;

import net.onlinetesting.model.Role;
import net.onlinetesting.model.User;
import net.onlinetesting.repository.RoleRepository;
import net.onlinetesting.repository.UserRepository;
import net.onlinetesting.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) throws DataIntegrityViolationException {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null){
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.getOne(2L));
            user.setRoles(roles);
        }
        userRepository.save(user);

        logger.info("User with email: {} registered", user.getEmail());
    }

    @Override
    public List<User> getAllUsers() {
       return  userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.getOne(Long.parseLong(id));
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
