package com.whispr.chat.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chat_rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    @Id
    private String id;  // UUID string

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "chat_room_participants",
            joinColumns = @JoinColumn(name = "chat_room_id")
    )
    @Column(name = "participant")
    private Set<String> participants = new HashSet<>();
}
