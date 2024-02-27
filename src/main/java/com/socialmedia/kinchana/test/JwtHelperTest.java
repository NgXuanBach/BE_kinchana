package com.socialmedia.kinchana.test;

import com.socialmedia.kinchana.utils.JwtHelper;

public class JwtHelperTest {
    public static void main(String[] args) {
        JwtHelper jwtHelper = new JwtHelper();
        System.out.println(jwtHelper.generateSecretKey());
        System.out.println(jwtHelper.generateToken("phat@gmail.com"));
        System.out.println(jwtHelper.decodeToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwaGF0QGdtYWlsLmNvbSJ9.-Em8PCi85K7DCvoerZM2y2uiAvgfhQy3UY7q6Ui2GWY").getSubject());
    }
}
