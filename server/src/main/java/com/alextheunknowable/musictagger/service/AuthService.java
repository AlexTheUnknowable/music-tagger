package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.exception.InsufficientPermissionsException;
import com.alextheunknowable.musictagger.exception.UnauthorizedException;
import com.alextheunknowable.musictagger.model.User;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthService {
    private final UserService userService;
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public User getUserFromPrincipal(Principal principal) {
        if (principal == null) throw new UnauthorizedException("Authentication required");
        return userService.getUserByUsername(principal.getName());
    }

    public int getUserIdFromPrincipal(Principal principal) {
        return getUserFromPrincipal(principal).getId();
    }

    public void assertUserCanModify(Principal principal, int uploaderId, String errorMessage) {
        User user = getUserFromPrincipal(principal);
        if (!isAdmin(user) && user.getId() != uploaderId) {
            throw new InsufficientPermissionsException(errorMessage);
        }
    }

    private boolean isAdmin(User user) {
        return user.getRole().equals("ROLE_ADMIN");
    }

}
