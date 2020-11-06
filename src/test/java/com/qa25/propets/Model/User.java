package com.qa25.propets.Model;

public class User {
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmail(String email) {
        if(email != null) {
            this.email = email;
        }else{
            this.email = "";
        }
        return this;
    }

    public User setPassword(String password) {
        if(password != null) {
            this.password = password;
        }else{
            this.password = "";
        }
        return this;
    }

    public User setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
        return this;
    }

    @Override
    public String toString() {
        return "User: " +
                "Name= " + name +
                ", Email= " + email +
                ", Password= " + password;
    }
}
