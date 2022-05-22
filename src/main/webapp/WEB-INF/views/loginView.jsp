<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 22.05.2022
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Login Page</h3>

    <p style="color: red">${errorString}</p>

    <form method="POST" action="${pageContext.request.contextPath}/login">
        <input type="hidden" name="redirectId" value="${param.redirectId}" />
        <table border="0">
            <tr>
                <td>User Name</td>
                <td><input type="text" name="userName" value= "${user.userName}" /> </td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" value= "${user.password}" /> </td>
            </tr>

            <tr>
                <td colspan ="2">
                    <input type="submit" value= "Submit" />
                    <a href="${pageContext.request.contextPath}/">Cancel</a>
                </td>
            </tr>
        </table>

        <p style="color: blue">Login with</p>
    </form>

    employee1/123 <br>
    manager1/123
</body>
</html>
