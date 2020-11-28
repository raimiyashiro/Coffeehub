package com.rgoncami.coffeehub.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cafe_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private UUID id;
    @Column(precision = 20, nullable = false, unique = true)
    private String nickname;
    @Column(precision = 50, nullable = false)
    private String title;

    @ManyToMany
    @JoinTable(name = "user_rooms", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private Set<Room> rooms;

    @ManyToMany
    @JoinTable(name = "room_likes", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private Set<Room> likedRooms;

    @ManyToMany
    @JoinTable(name = "room_owners", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private Set<Room> ownedRooms;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<Message> messages;

}
