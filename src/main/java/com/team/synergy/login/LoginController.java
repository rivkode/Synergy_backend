package com.team.synergy.login;

import com.team.synergy.login.LoginService;
import com.team.synergy.member.MemberJoinRequest;
import com.team.synergy.member.MemberLoginRequest;
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
    private final LoginService loginService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinRequest dto) {
        loginService.join(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok().body("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginRequest dto, HttpServletResponse response) {
        String token = loginService.login(dto.getEmail(), dto.getPassword());

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
