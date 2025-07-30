package com.cubeai.domain.member.entity;

import com.cubeai.domain.common.BaseEntity;
import com.cubeai.domain.project.entity.Project;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 50, nullable = false)
    private String oAuthId;

    @Column(length = 30)
    private String nickname;

    @Column(length = 512)
    private String profileUrl;

    private LocalDateTime deletedAt;

    @Builder
    public Member(String oAuthId, String nickname, String profileUrl) {
        this.oAuthId = oAuthId;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
