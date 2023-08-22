package com.team.synergy.member;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.dto.response.MemberGetResponse;
import com.team.synergy.utils.JwtUtil;
import com.team.synergy.member.dto.request.MemberSignInRequest;
import com.team.synergy.member.dto.request.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder encoder;

    private Long expiredTimeMs = 1000 * 24 * 60 * 60L; // 1일

    @Value("${jwt.token.secret}")
    private String secretKey;

    public String signup(MemberSignUpRequest request) {
        memberRepository.findByEmail(request.getEmail())
                .ifPresent(member -> {
                    throw new AppException((ErrorCode.MEMBERNAME_DUPLICATED), request.getEmail() + "는 이미 존재합니다");
                });
        memberRepository.save(new Member(request.getName(), encoder.encode(request.getPassword()), request.getEmail()));

        return "SUCCESS";
    }

    public String login(MemberSignInRequest request) {
        Member savedMember = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, request.getEmail() + "은 존재하지 않습니다"));

        if (!encoder.matches(request.getPassword(), savedMember.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력하였습니다");
        }

        return JwtUtil.createJwt(savedMember.getEmail(), secretKey, expiredTimeMs);

    }

    public Member findMemberById(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, memberId + " : id 인 멤버가 없습니다"));
    }

    public Page<MemberGetResponse> searchMember(Pageable pageable, String keyword) {
        Page<Member> members = memberRepository.findByNameContaining(keyword, pageable);
        Page<MemberGetResponse> memberGetResponses = MemberGetResponse.toResponse(members);

        return memberGetResponses;

    }
}
