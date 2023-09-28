package com.team.synergy.config.auth;

import com.team.synergy.config.JwtFilter;
import com.team.synergy.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {
    private final MemberService memberService;

    @Value("${jwt.token.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/members/signup", "/members/login", "/subscribe", "/hello").permitAll() // jwt사용하는 경우 씀
                .antMatchers("/**").authenticated() // 윗줄의 api를 제외한 나머지는 모두 인증을 필요로 함
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt사용하는 경우 씀
                .and().logout().logoutSuccessUrl("/")
                .and()
                .addFilterBefore(new JwtFilter(memberService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
