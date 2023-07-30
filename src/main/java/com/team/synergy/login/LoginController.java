package com.team.synergy.login;

import com.team.synergy.member.MemberService;
import com.team.synergy.member.dto.MemberSignInRequest;
import com.team.synergy.member.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class LoginController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberSignUpRequest dto) {
        memberService.signUp(dto);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberSignInRequest dto, HttpServletResponse response) {
        String token = memberService.signIn(dto);

        ResponseCookie cookie = ResponseCookie.from("ACESSTOKEN", token)
                .maxAge(7 * 24 * 60 * 60) // 쿠키 만료 7일 설정
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build()
                ;

        response.setHeader("Set-Cookie", cookie.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

}
