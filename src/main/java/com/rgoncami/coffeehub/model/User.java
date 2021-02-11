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
    @JsonManagedReference(value = "user-messages")
    private Set<Message> messages;

    public boolean isValid() {
        return this.hasValidNickName() &&
                this.hasValidTitle();
    }

    private boolean hasValidTitle() {
        return this.getTitle() != null &&
                this.getTitle().length() > 0 && this.getTitle().length() <= 50;
    }

    public boolean hasValidNickName() {
        return this.getNickname() != null &&
                this.getNickname().length() > 0 && this.getNickname().length() <= 20;
    }

}
