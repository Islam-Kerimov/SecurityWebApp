package com.kerimovikh.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserAccount {

    public static final String MALE = "M";
    public static final String FEMALE = "F";

    private String userName;
    private String gender;
    private String password;

    private List<String> role;

    public UserAccount() {

    }

    public UserAccount(String userName, String password, String gender, String... role) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;

        this.role = new ArrayList<>();
        if (role != null) {
            Collections.addAll(this.role, role);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
