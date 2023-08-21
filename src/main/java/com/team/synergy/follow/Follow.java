package com.team.synergy.follow;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Builder
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private Member follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private Member following;

    @Column(name = "follow_status")
    @Enumerated(EnumType.STRING)
    private FollowStatus status; // follow 상태 [FOLLOW, UNFOLLOW]


    public void setStatus(FollowStatus status) {
        this.status = status;
    }


    public static Follow createFollow(Member follower, Member following, FollowStatus status) {
        return Follow.builder()
                .follower(follower)
                .following(following)
                .status(status)
                .build();
    }

    public Follow(Member follower, Member following) {
        this.follower = follower;
        this.following = following;
        this.status = FollowStatus.FOLLOW;
    }

    public void cancelFollow() {
        if (getStatus() == FollowStatus.UNFOLLOW) {
            throw new AppException(ErrorCode.INVALID_DATA, "이미 UNFOLLOW입니다");
        } else {
            this.setStatus(FollowStatus.UNFOLLOW);
        }
    }
}
