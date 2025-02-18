package com.example.test.omnivore2trendithon2025.member.domain;

import com.example.test.omnivore2trendithon2025.global.entity.BaseEntity;
import com.example.test.omnivore2trendithon2025.global.entity.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean firstLogin;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    private String name;

    private String nickname;

    private String picture;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    private String introduction;

    @Builder
    private Member(Status status, Role role,
                   String email, String name,
                   String nickname, String picture,
                   SocialType socialType,
                   boolean firstLogin,
                   String introduction) {
        this.status = status;
        this.role = role;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.picture = picture;
        this.socialType = socialType;
        this.firstLogin = firstLogin;
        this.introduction = introduction;
    }

    public void update(String nickname) {
        if (isUpdateRequired(nickname)) {
            this.nickname = nickname;
        }
    }

    private boolean isUpdateRequired(String updateNickname) {
        return !this.nickname.equals(updateNickname);
    }

    public void updatePicture(String picture) {
        this.picture = picture;
    }
}
