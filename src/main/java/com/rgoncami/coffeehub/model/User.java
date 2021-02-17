package com.rgoncami.coffeehub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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

    @JsonIgnoreProperties("users")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_rooms", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user-messages")
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Set<Message> messages = new HashSet<>();

    public boolean validate() {
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