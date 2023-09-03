package com.team.synergy.member;

import com.team.synergy.member.dto.request.MemberSignInRequest;
import com.team.synergy.member.dto.request.MemberSignUpRequest;
import com.team.synergy.member.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<CreateMemberResponse> join(@RequestBody MemberSignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginMemberResponse> login(@RequestBody MemberSignInRequest request) {
        return ResponseEntity.ok().body(memberService.login(request));
    }

    @GetMapping("/me/info")
    public ResponseEntity<MeInfoResponse> getMemberFromToken(HttpServletRequest request) {
        Member member = memberService.findMemberByToken(request);

        return ResponseEntity.ok()
                .body(MeInfoResponse.from(member));

    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoMemberResponse> getMember(@PathVariable("id") String memberId) {

        return ResponseEntity.ok()
                .body(memberService.memberInfo(memberId));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MemberGetResponse>> searchMemberList(@PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String keyword) {
        Page<MemberGetResponse> memberGetResponses = memberService.searchMember(pageable, keyword);

        return ResponseEntity.ok()
                .body(memberGetResponses);
    }
}
