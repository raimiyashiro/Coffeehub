package com.rgoncami.coffeehub;

import com.rgoncami.coffeehub.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserTest {

    User valid, invalid;

    @BeforeEach
    public void setup() {
        valid.setId(UUID.randomUUID());
        valid.setNickname("FranzK4fk4");
        valid.setTitle("Knight");

        invalid.setId(UUID.randomUUID());
        invalid.setNickname("ThisNicknameIsTooooooLong");
        invalid.setTitle(null);
    }

    @Test
    void whenUserIsValid_shouldReturnTrue() {
        boolean isValid = valid.isValid();
        Assertions.assertEquals(true, isValid);
    }

    @Test
    void whenUserIsInvalid_shouldReturnFalse() {
        boolean isInvalid = invalid.isValid();
        Assertions.assertEquals(false, invalid.isValid());
    }
}