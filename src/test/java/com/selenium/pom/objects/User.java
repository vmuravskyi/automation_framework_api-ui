package com.selenium.pom.objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {
    private String username;
    private String password;
    private String email;

    public User() {

    }

    /**
     * Constructor needed for parsing data from ConfigLoader
     *
     * @param userName
     * @param password
     */
    public User(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("password", password)
                .append("email", email)
                .toString();
    }
}
