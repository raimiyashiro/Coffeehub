package com.rgoncami.coffeehub.service;

import com.rgoncami.coffeehub.model.User;
import com.rgoncami.coffeehub.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User findByNickname(String nickname) {
        return this.repo.findByNickname(nickname);
    }

    public User insert(User user) {
        if (this.findByNickname(user.getNickname()) == null) {
            return this.repo.save(user);
        }
        throw new DuplicateKeyException("The given nickname already exists, pick another one");
    }
}
