package com.kerimovikh.servlet;

import com.kerimovikh.bean.UserAccount;
import com.kerimovikh.dao.DataDao;
import com.kerimovikh.utils.AppUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        UserAccount userAccount = DataDao.findUser(userName, password);

        if (userAccount == null) {
            String errorMessage = "Invalid userName or Password";

            req.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(req, resp);
            return;
        }

        AppUtils.storeLoginedUser(req.getSession(), userAccount);

        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(req.getParameter("redirectId"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String requestUri = AppUtils.getRedirectAfterLoginUrl(req.getSession(), redirectId);
        resp.sendRedirect(Objects.requireNonNullElseGet(requestUri, () -> req.getContextPath() + "/userInfo"));
    }
}
