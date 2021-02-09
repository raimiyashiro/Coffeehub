package com.rgoncami.coffeehub;

import com.rgoncami.coffeehub.exception.exceptions.RoomCreationException;
import com.rgoncami.coffeehub.model.Room;
import com.rgoncami.coffeehub.repo.RoomRepository;
import com.rgoncami.coffeehub.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class RoomServiceTest {

    @TestConfiguration
    static class RoomServiceTestConfiguration {
        @Bean
        public RoomService service() {
            return new RoomService();
        }
    }

    @Autowired
    private RoomService service;

    @MockBean
    private RoomRepository repo;

    @BeforeEach
    public void setup() {
        Room room = new Room();
        room.setName("The Golden Hall");

        Mockito.when(this.repo.findByName(room.getName()))
                .thenReturn(room);

        Room room1 = new Room();
        room1.setName("A new Room");
        Mockito.when(this.repo.save(Mockito.any(Room.class)))
                .thenReturn(room1);
    }

    @Test
    void shouldReturnRoom_whenRoomExists() {
        String name = "The Golden Hall";
        Room room = this.service.findByName(name);

        Assertions.assertNotNull(room);
    }

    @Test
    void shouldThrowException_whenRoomIsNotFOund() {
        Assertions.assertThrows(RoomNotFoundException.class, () -> {
            this.service.findByName("Heorot");
        });
    }

    @Test
    void shouldNotInsert_whenRoomExists() {
        Room room = new Room();
        room.setId(UUID.randomUUID());
        room.setName("The Golden Hall");

        Assertions.assertThrows(RoomCreationException.class, () -> {
            this.service.insert(room);
        });
    }

    @Test
    void shouldInsert_whenRoomDoesNotExist() {
        Room room = new Room();
        room.setId(UUID.randomUUID());
        room.setName("A new Room");

        Room insert = this.service.insert(room);
        Assertions.assertEquals(room.getName(), insert.getName());
    }
}
