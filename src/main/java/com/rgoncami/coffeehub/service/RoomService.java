package com.rgoncami.coffeehub.service;

import com.rgoncami.coffeehub.model.Room;
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
        throw new DuplicateKeyException("There is already a Room with name: " + room.getName());
    }
}
