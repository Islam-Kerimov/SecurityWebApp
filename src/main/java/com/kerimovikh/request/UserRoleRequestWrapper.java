package com.kerimovikh.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.security.Principal;
import java.util.List;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private String user;
    private List<String> role;
    private HttpServletRequest request;

    public UserRoleRequestWrapper(String user, List<String> role, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.role = role;
        this.request = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (this.role == null) {
            return this.request.isUserInRole(role);
        }
        return this.role.contains(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if (user == null) {
            return request.getUserPrincipal();
        }

        return new Principal() {
            @Override
            public String getName() {
                return user;
            }
        };
    }
}
