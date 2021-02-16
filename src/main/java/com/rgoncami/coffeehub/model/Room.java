package com.rgoncami.coffeehub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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

    @JsonIgnoreProperties("rooms")
    @ManyToMany(mappedBy = "rooms", fetch = FetchType.LAZY)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    public Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "room")
    @JsonManagedReference(value = "room-messages")
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Set<Message> messages = new HashSet<>();

}
