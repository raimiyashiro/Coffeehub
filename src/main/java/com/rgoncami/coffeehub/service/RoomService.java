package com.rgoncami.coffeehub.service;

import com.rgoncami.coffeehub.exception.enums.RoomError;
import com.rgoncami.coffeehub.exception.exceptions.RoomCreationException;
import com.rgoncami.coffeehub.exception.exceptions.RoomNotFoundException;
import com.rgoncami.coffeehub.model.Room;
import com.rgoncami.coffeehub.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repo;

    public Room findByName(String name) {
        Room room = this.repo.findByName(name);

        if (room != null) {
            return room;
        }

        throw new RoomNotFoundException(RoomError.ROOM_DOES_NOT_EXIST);
    }

    public Room insert(Room room) {
        Room find = null;
        try {
            find = this.findByName(room.getName());
        } catch (RoomNotFoundException e) {
        }

        if (find == null) {
            room.setId(UUID.randomUUID());
            return this.repo.save(room);
        }

        throw new RoomCreationException(RoomError.ROOM_NAME_IS_UNAVAILABLE);
    }

    public List<Room> list() {
        return this.repo.findAll();
    }
}
