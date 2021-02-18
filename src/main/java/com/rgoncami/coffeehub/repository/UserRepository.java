package com.rgoncami.coffeehub.repository;

import com.rgoncami.coffeehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByNickname(String nickname);
}
