package com.socialmedia.kinchana.config;

import com.google.gson.Gson;
import com.socialmedia.kinchana.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationEntryPointConfig implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        BaseResponse baseResponse = new BaseResponse();
        if (authException instanceof BadCredentialsException) {
            baseResponse.setStatusCode(401);
            baseResponse.setMessage("Unauthorized");
        } else {
            baseResponse.setStatusCode(403);
            baseResponse.setMessage("Access denied.");
        }
        response.setContentType("application/json");
        response.setStatus(HttpStatus.OK.value());

        response.getWriter().write(new Gson().toJson(baseResponse));
    }
}
