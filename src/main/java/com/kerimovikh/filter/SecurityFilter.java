package com.kerimovikh.filter;

import com.kerimovikh.bean.UserAccount;
import com.kerimovikh.request.UserRoleRequestWrapper;
import com.kerimovikh.utils.AppUtils;
import com.kerimovikh.utils.SecurityUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();

        UserAccount loginedUser = AppUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            String userName = loginedUser.getUserName();
            List<String> role = loginedUser.getRole();

            wrapRequest = new UserRoleRequestWrapper(userName, role, request);
        }

        if (SecurityUtils.isSecurityPage(request)) {
            if (loginedUser == null) {
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), request.getRequestURI());

                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);

                return;
            }
        }

        boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
        if (!hasPermission) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        filterChain.doFilter(wrapRequest, response);
    }
}
