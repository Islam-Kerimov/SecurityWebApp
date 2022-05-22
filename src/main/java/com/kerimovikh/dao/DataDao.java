package com.kerimovikh.dao;

import com.kerimovikh.bean.UserAccount;
import com.kerimovikh.config.SecurityConfig;

import java.util.HashMap;
import java.util.Map;

public class DataDao {

    private static final Map<String, UserAccount> mapUsers = new HashMap<>();

    static {
        initUsers();
    }

    private static void initUsers() {
        UserAccount emp = new UserAccount("employee1", "123", UserAccount.MALE, SecurityConfig.ROLE_EMPLOYEE);

        UserAccount mng = new UserAccount("manager1", "123", UserAccount.MALE, SecurityConfig.ROLE_EMPLOYEE, SecurityConfig.ROLE_MANAGER);

        mapUsers.put(emp.getUserName(), emp);
        mapUsers.put(mng.getUserName(), mng);
    }

    public static UserAccount findUser(String userName, String password) {
        UserAccount userAccount = mapUsers.get(userName);
        if (userAccount != null && userAccount.getPassword().equals(password)) {
            return userAccount;
        } else {
            return null;
        }
    }

}
