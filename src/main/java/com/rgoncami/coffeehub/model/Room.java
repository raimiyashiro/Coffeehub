package com.rgoncami.coffeehub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "room")
public class Room {

    @Id
    private UUID id;

    @Column(precision = 50, nullable = false, unique = true)
    private String name;

    @JsonIgnoreProperties("rooms")
    @ManyToMany(mappedBy = "rooms", fetch = FetchType.LAZY)
    public Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "room")
    @JsonManagedReference(value = "room-messages")
    private Set<Message> messages = new HashSet<>();

    public Room() {
    }

    public Room(UUID id, String name, Set<User> users, Set<Message> messages) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.messages = messages;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
