package com.rgoncami.coffeehub.resource;

import com.rgoncami.coffeehub.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserResource {

    @GetMapping("/users")
    public void list() {

    }

    @GetMapping("/users/{nickname}")
    public void get(@PathVariable String nickname) {

    }

    @PostMapping("/users")
    public void post(@RequestBody User user) {

    }
}
