package com.rgoncami.coffeehub.service;

import com.rgoncami.coffeehub.exception.enums.UserError;
import com.rgoncami.coffeehub.exception.exceptions.UserCreationException;
import com.rgoncami.coffeehub.exception.exceptions.UserNotFoundException;
import com.rgoncami.coffeehub.model.User;
import com.rgoncami.coffeehub.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User findByNickname(String nickname) {
        User user = this.repo.findByNickname(nickname);

        if (user != null) {
            return user;
        }

        throw new UserNotFoundException(UserError.USER_DOES_NOT_EXIST);
    }

    public User insert(User user) {
        User find = null;
        try {
            find = this.findByNickname(user.getNickname());
        } catch (UserNotFoundException e) {
        }

        if (find == null) {
            user.setId(UUID.randomUUID());
            User result = this.repo.save(user);

            return result;
        }

        throw new UserCreationException(
                UserError.USER_NICKNAME_ALREADY_EXISTS);
    }

    public List<User> list() {
        return this.repo.findAll();
    }
}
