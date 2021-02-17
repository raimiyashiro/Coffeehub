package com.rgoncami.coffeehub.resource;

import com.rgoncami.coffeehub.model.Room;
import com.rgoncami.coffeehub.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomResource extends BaseResource {

    @Autowired
    RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> list() {
        return this.roomService.list();
    }

    @GetMapping("/rooms/{name}")
    public Room get(@PathVariable String name) {

        return this.roomService.findByName(name);
    }

    @PostMapping("/rooms")
    public Room post(@RequestBody Room room) {

        return this.roomService.insert(room);
    }
}
