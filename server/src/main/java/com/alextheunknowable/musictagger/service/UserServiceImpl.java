package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.UserDao;
import com.alextheunknowable.musictagger.model.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    private final UserDao userDao;
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User createUser(User newUser) {
        return userDao.createUser(newUser);
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }
}
