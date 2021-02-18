package com.rgoncami.coffeehub.service;

import com.rgoncami.coffeehub.exception.enums.UserError;
import com.rgoncami.coffeehub.exception.exceptions.UserCreationException;
import com.rgoncami.coffeehub.exception.exceptions.UserNotFoundException;
import com.rgoncami.coffeehub.model.User;
import com.rgoncami.coffeehub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByNickname(String nickname) {
        return this.userRepository.findByNickname(nickname).orElseThrow(() -> new UserNotFoundException(UserError.USER_DOES_NOT_EXIST));
    }

    public User insert(User user) {
        if (nicknameIsTaken(user.getNickname())) {
            throw new UserCreationException(UserError.USER_NICKNAME_ALREADY_EXISTS);
        }

        user.setId(UUID.randomUUID());
        return this.userRepository.save(user);
    }

    public List<User> list() {
        return this.userRepository.findAll();
    }

    private boolean nicknameIsTaken(String nickname) {
        return this.userRepository.findByNickname(nickname).isPresent();
    }

}
