package com.socialmedia.kinchana.config;

import com.socialmedia.kinchana.filter.JwtFilter;
import com.socialmedia.kinchana.provider.CustomAuthenticateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors().and().csrf().disable() // Tắt cấu hình liên quan đến tấn công CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // khai báo không sử dụng session trong project
                .and()
                .authorizeHttpRequests()   // Quy định lại các rule liên quan tới chứng thực cho link được gọi
                .antMatchers("/signin","/signin/**", "/signup","/signup/**").permitAll()  // cho phép vào luôn ko cần chứng thực
//                .antMatchers("/user/**","/user").hasAuthority("user")
                .anyRequest().authenticated() // Tất cả các link còn lại cần phải chứng thực
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)//add filter cua minh truoc filtersecurity
                .build();
    }
    @Autowired
    private CustomAuthenticateProvider authenticateProvider;
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticateProvider)
                .build();
    }

}
