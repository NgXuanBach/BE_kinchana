package com.socialmedia.kinchana.filter;

import com.socialmedia.kinchana.entity.UserEntity;
import com.socialmedia.kinchana.repository.UserRepository;
import com.socialmedia.kinchana.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.hibernate.annotations.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtHelper jwtHelper;
    private Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("Authentication");
            if(token!=null && token.startsWith("Bearer ")){
                token = token.substring(7);
                Claims claims = null;
                try {
                    claims = jwtHelper.decodeToken(token);
                }catch (Exception e){
                    logger.warn(e.getMessage());
                }
                if(claims!=null){
                    UserEntity user = userRepository.findByGmail(claims.getSubject());
                    if(user!=null){
                        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getGmail(),"",authorities);
                        SecurityContext context = SecurityContextHolder.getContext();
                        context.setAuthentication(authenticationToken);
                    }
                }
            }

        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}
