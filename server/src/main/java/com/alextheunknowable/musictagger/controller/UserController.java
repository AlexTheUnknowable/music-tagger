package com.alextheunknowable.musictagger.controller;

import com.alextheunknowable.musictagger.dao.*;
import com.alextheunknowable.musictagger.exception.DaoException;
import com.alextheunknowable.musictagger.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public List<User> list() {
        try {
            return userDao.getUsers();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "could not get cards: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        try {
            return userDao.getUserById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "could not get card: " + e.getMessage());
        }
    }

}
