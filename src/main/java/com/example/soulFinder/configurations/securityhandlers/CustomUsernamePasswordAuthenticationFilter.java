package com.example.soulFinder.configurations.securityhandlers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        // Сохраните сообщение об ошибке в сессии
        request.getSession().setAttribute("error", "Неверное имя пользователя или пароль");

        super.unsuccessfulAuthentication(request, response, failed);
    }
}
