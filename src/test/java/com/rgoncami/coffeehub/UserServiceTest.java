package com.rgoncami.coffeehub;

import com.rgoncami.coffeehub.model.User;
import com.rgoncami.coffeehub.repo.UserRepository;
import com.rgoncami.coffeehub.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestConfiguration {
        @Bean
        public UserService service() {
            return new UserService();
        }
    }

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repo;

    @BeforeEach
    public void setup() {
    }

    @Test
    void whenNicknameExists_mustFindUser() {
        String nickname = "coolnam3";
        User user = this.service.findByNickname(nickname);
        Assertions.assertEquals(nickname, user.getNickname());
    }

    @Test
    void whenNicknameDoesNotExist_mustInsertUser() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setNickname("darkS0ldi3r");
        user.setTitle("King of Wessex");

        User insert = this.service.insert(user);
        Assertions.assertEquals(user.getClass(), insert.getClass());
    }

    @Test
    void whenNickNameExists_mustNotInsert() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setNickname("romanP4ladin");
        user.setTitle("Roman Noble");

        Assertions.assertThrows(DuplicateKeyException.class, () -> {
            this.service.insert(user);
        });

    }
}
