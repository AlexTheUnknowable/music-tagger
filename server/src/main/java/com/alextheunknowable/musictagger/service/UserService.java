package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.model.User;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(int userId);
    User getUserByUsername(String username);

    User createUser(User newUser);

    User updateUser(User user);
}
