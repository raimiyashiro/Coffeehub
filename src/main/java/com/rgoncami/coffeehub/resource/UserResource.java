package com.rgoncami.coffeehub.resource;

import com.rgoncami.coffeehub.model.User;
import com.rgoncami.coffeehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource extends BaseResource {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> list() {
        return this.userService.list();
    }

    @GetMapping("/users/{nickname}")
    public User get(@PathVariable String nickname) {

        return this.userService.findByNickname(nickname);
    }

    @PostMapping("/users")
    public User post(@RequestBody User user) {

        return this.userService.insert(user);
    }
}
