package com.mydata.service.impl;

import com.mydata.entity.User;
import com.mydata.exception.InvalidUserIdException;
import com.mydata.exception.UserAlreadyExistsException;
import com.mydata.exception.UserNotFoundException;
import com.mydata.repository.UserRepository;
import com.mydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder1) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder1;
    }

    @Override
    public User createUser(User user) {
        if (user.getId() != null) {
            throw new InvalidUserIdException("New users cannot have an ID");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username)
                .orElseThrow((() -> new UserNotFoundException("User not found with username: " + username))));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setRole(updatedUser.getRole());
        return userRepository.save(existingUser);
    }
    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }
}