package com.mydata.service;

import com.mydata.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    User updateUser(Long id, User updatedUser);
    void deleteUserById(Long id);

}