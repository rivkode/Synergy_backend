package com.team.synergy.chat;

import com.team.synergy.member.Member;
import com.team.synergy.room.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member receiver;

    @Lob
    @Column(nullable = false)
    private String content;

    @Builder
    public Chat(Room room, Member sender, Member receiver, String content) {
        this.room = room;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}
