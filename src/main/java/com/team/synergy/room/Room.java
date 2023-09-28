package com.team.synergy.room;

import com.team.synergy.BaseTime;
import com.team.synergy.chat.Chat;
import com.team.synergy.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Room extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member1;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member2;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Chat> chats = new ArrayList<>();

    @Builder
    public Room(Member member1, Member member2) {
        this.member1 = member1;
        this.member2 = member2;
    }

    public Member getPartner(String memberId) {
        if (member2.hasSameId(memberId)) {
            return member2;
        }
        return member1;
    }

    public Chat getLatestChat() {
        return chats.get(0);
    }

    public boolean hasMessage() {
        return !chats.isEmpty();
    }

}
