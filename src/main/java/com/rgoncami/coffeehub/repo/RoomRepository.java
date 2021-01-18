package com.rgoncami.coffeehub.repo;

import com.rgoncami.coffeehub.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    Room findByName(String name);
}
