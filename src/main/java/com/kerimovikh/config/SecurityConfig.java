package com.kerimovikh.config;

import java.util.*;

public class SecurityConfig {

    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        List<String> urlPattern1 = new ArrayList<>();

        urlPattern1.add("/userInfo");
        urlPattern1.add("/employeeTask");

        mapConfig.put(ROLE_EMPLOYEE, urlPattern1);

        List<String> urlPattern2 = new ArrayList<>();

        urlPattern2.add("/userInfo");
        urlPattern2.add("/managerTask");

        mapConfig.put(ROLE_MANAGER, urlPattern2);
    }

    public static Set<String> getAllAppRole() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternForRole(String role) {
        return mapConfig.get(role);
    }
}
