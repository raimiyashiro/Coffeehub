package com.rgoncami.coffeehub.service;

import com.rgoncami.coffeehub.exception.enums.UserError;
import com.rgoncami.coffeehub.exception.exceptions.UserCreationException;
import com.rgoncami.coffeehub.exception.exceptions.UserNotFoundException;
import com.rgoncami.coffeehub.model.User;
import com.rgoncami.coffeehub.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (this.findByNickname(user.getNickname()) == null) {
            return this.repo.save(user);
        }

        throw new UserCreationException(
                UserError.USER_NICKNAME_ALREADY_EXISTS);
    }
}
