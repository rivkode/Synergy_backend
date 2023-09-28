package com.team.synergy.config.auth.login;

import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpServletRequest httpServletRequest;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isStringClass = String.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isStringClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object memberId = httpServletRequest.getAttribute("memberId");
        if (memberId == null) {
            throw new AppException(ErrorCode.INVALID_DATA, "memberId가 없습니다");
        }
        return memberId.toString();
    }
}
