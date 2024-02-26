package com.socialmedia.kinchana.payload.request;
public class SigninRequest {
    private String email;
    private String password;

    public SigninRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SigninRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
