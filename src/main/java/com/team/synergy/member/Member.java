package com.team.synergy.member;

import com.team.synergy.BaseTime;
import com.team.synergy.apply.Apply;
import com.team.synergy.comment.Comment;
import com.team.synergy.post.Post;
import com.team.synergy.project.Project;
import com.team.synergy.projectmember.ProjectMember;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
public class Member extends BaseTime {
    @Id
    @Column(name = "member_id")
    private String id;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_email")
    private String email;

    private String avatar;

    private String backgroundImage;

    private String major;

    private String temperature;

    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(
            mappedBy = "member"
    )
    private List<Post> posts = new ArrayList<>();

    @OneToMany(
            mappedBy = "member"
    )
    private List<Apply> applyList = new ArrayList<>();

    @OneToMany(
            mappedBy = "member"
    )
    private List<ProjectMember> projects = new ArrayList<>();

    @OneToMany(
            mappedBy = "member"
    )
    private List<Comment> comments = new ArrayList<>();


    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean hasSameId(String id) {
        return this.id.equals(id);
    }



    public Member(String name, String password, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.password = password;
        this.email = email;
        this.avatar = "";
        this.backgroundImage = "";
        this.temperature = "36.5";
    }

    protected Member() {

    }


}
