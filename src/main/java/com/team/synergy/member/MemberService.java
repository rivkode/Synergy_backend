package com.team.synergy.member;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.dto.response.CreateMemberResponse;
import com.team.synergy.member.dto.response.InfoMemberResponse;
import com.team.synergy.member.dto.response.LoginMemberResponse;
import com.team.synergy.member.dto.response.MemberGetResponse;
import com.team.synergy.utils.JwtUtil;
import com.team.synergy.member.dto.request.MemberSignInRequest;
import com.team.synergy.member.dto.request.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder encoder;

    private Long expiredTimeMs = 1000 * 24 * 60 * 30 * 60L; // 30일

    @Value("${jwt.token.secret}")
    private String secretKey;

    @Transactional
    public CreateMemberResponse signup(MemberSignUpRequest request) {
        memberRepository.findByEmail(request.getEmail())
                .ifPresent(member -> {
                    throw new AppException((ErrorCode.MEMBERNAME_DUPLICATED), request.getEmail() + "는 이미 존재합니다");
                });
        Member savedMember = memberRepository.save(new Member(request.getName(), encoder.encode(request.getPassword()), request.getEmail()));

        return CreateMemberResponse.from(savedMember);
    }

    public LoginMemberResponse login(MemberSignInRequest request) {
        Member findMember = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_DATA, request.getEmail() + "은 존재하지 않습니다"));

        if (!encoder.matches(request.getPassword(), findMember.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력하였습니다");
        }

        String token = JwtUtil.createJwt(findMember.getId(), secretKey, expiredTimeMs);

        return LoginMemberResponse.from(token);

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

    @Transactional
    public InfoMemberResponse memberInfo(String memberId) {
        Member member = findMemberById(memberId);
        return InfoMemberResponse.from(member);
    }

    public Member findMemberIdByToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authorization.split(" ")[1];
        String memberId = JwtUtil.getId(token, secretKey);

        Member member = findMemberById(memberId);
        return member;
    }
}
