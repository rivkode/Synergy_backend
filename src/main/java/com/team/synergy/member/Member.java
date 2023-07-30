package com.team.synergy.member;

import com.team.synergy.BaseTime;
import lombok.*;

import javax.persistence.*;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Member(String name, String password, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.password = password;
        this.email = email;
    }

    protected Member() {

    }


}
