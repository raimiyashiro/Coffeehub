package com.rgoncami.coffeehub.service;

import com.rgoncami.coffeehub.exception.enums.RoomError;
import com.rgoncami.coffeehub.exception.exceptions.RoomCreationException;
import com.rgoncami.coffeehub.exception.exceptions.RoomNotFoundException;
import com.rgoncami.coffeehub.model.Room;
import com.rgoncami.coffeehub.model.User;
import com.rgoncami.coffeehub.repository.RoomRepository;
import com.rgoncami.coffeehub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public Room findByName(String name) {
        return this.roomRepository.findByName(name)
                .orElseThrow(() -> new RoomNotFoundException(RoomError.ROOM_DOES_NOT_EXIST));
    }

    public Room insert(Room room) {
        if (nameIsTaken(room.getName())) {
            throw new RoomCreationException(RoomError.ROOM_NAME_IS_UNAVAILABLE);
        }

        room.setId(UUID.randomUUID());

        associateUsers(room);

        return this.roomRepository.save(room);
    }

    public List<Room> list() {
        return this.roomRepository.findAll();
    }

    private boolean nameIsTaken(String name) {
        return this.roomRepository.findByName(name).isPresent();
    }

    private void associateUsers(Room room) {
        List<Optional<User>> users = room.getUsers().stream()
                .map(User::getId)
                .map(id -> userRepository.findById(id))
                .filter(Optional::isPresent)
                .collect(Collectors.toList());

        users.forEach(user -> user.get().getRooms().add(room));
        users.forEach(user -> userRepository.save(user.get()));
    }

}
