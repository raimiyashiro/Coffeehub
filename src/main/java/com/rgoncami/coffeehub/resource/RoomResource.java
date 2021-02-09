package com.rgoncami.coffeehub.resource;

import com.rgoncami.coffeehub.model.Room;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomResource {

    @GetMapping("/rooms")
    public void list() {

    }

    @GetMapping("/rooms/{name}")
    public void get(@PathVariable String name) {

    }

    @PostMapping("/rooms")
    public void post(@RequestBody Room room) {

    }
}
