package com.rgoncami.coffeehub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cafe_user")
public class User {

    @Id
    private UUID id;

    @Column(precision = 20, nullable = false, unique = true)
    private String nickname;

    @Column(precision = 50, nullable = false)
    private String title;

    @JsonIgnoreProperties("users")
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user-messages")
    private Set<Message> messages = new HashSet<>();

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

    public User() {
    }

    public User(UUID id, String nickname, String title, Set<Room> rooms, Set<Message> messages) {
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.rooms = rooms;
        this.messages = messages;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
