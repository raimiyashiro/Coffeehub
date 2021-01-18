package com.rgoncami.coffeehub.service;

import com.rgoncami.coffeehub.exception.enums.RoomError;
import com.rgoncami.coffeehub.exception.exceptions.RoomCreationException;
import com.rgoncami.coffeehub.model.Room;
import com.rgoncami.coffeehub.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repo;

    public Room findByName(String name) {
        return this.repo.findByName(name);
    }

    public Room insert(Room room) {
        if (this.findByName(room.getName()) == null) {
            return this.repo.save(room);
        }
        throw new RoomCreationException(RoomError.ROOM_NAME_IS_UNAVAILABLE);
    }
}
