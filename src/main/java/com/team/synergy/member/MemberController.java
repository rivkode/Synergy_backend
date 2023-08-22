package com.team.synergy.member;

import com.team.synergy.member.dto.request.MemberSignInRequest;
import com.team.synergy.member.dto.request.MemberSignUpRequest;
import com.team.synergy.member.dto.response.MemberGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> join(@RequestBody MemberSignUpRequest request) {
        memberService.signup(request);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberSignInRequest request, HttpServletResponse response) {
        return ResponseEntity.ok().body(memberService.login(request));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MemberGetResponse>> searchMemberList(@PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String keyword) {
        Page<MemberGetResponse> memberGetResponses = memberService.searchMember(pageable, keyword);

        return ResponseEntity.ok()
                .body(memberGetResponses);
    }
}
