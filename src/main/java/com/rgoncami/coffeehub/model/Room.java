package com.rgoncami.coffeehub.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    private UUID id;

    @Column(precision = 50, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "rooms")
    public Set<User> users;

    @ManyToMany(mappedBy = "likedRooms")
    public Set<User> likes;

    @ManyToMany(mappedBy = "ownedRooms", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public Set<User> owners;

    @OneToMany(mappedBy = "room")
    @JsonManagedReference(value = "room-messages")
    private Set<Message> messages;
}
