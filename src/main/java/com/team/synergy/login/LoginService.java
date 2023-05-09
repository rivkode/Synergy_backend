package com.team.synergy.login;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.login.utils.JwtTokenUtil;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class LoginService {
    @Value("${jwt.token.secret}")
    private String key;

    private final BCryptPasswordEncoder encoder;
    private final MemberRepository memberRepository;

    private Long expiredTimeMs = 1000 * 60 * 60L;

    public String join(String email, String password) {
        // username 중복체크
        memberRepository.findByEmail(email)
                .ifPresent(member -> {
                    throw new AppException(ErrorCode.MEMBERNAME_DUPLICATED, email + "는 이미 있습니다.");
                });

        Member member = Member.builder()
                .email(email)
                .password(encoder.encode(password))
                .build();

        memberRepository.save(member);

        return "SUCCESS";
    }

    public String login(String email, String password) {
        // email 없음
        Member selectedMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.MEMBERNAME_NOT_FOUND, email + "이 없습니다."));

        if(!encoder.matches(password, selectedMember.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못입력하였습니다.");
        }

        // 앞에서 Exception 발생하지 않았을 경우 토큰 발행
        String token = JwtTokenUtil.createToken(selectedMember.getEmail(), key, expiredTimeMs);

        return token;
    }
}
