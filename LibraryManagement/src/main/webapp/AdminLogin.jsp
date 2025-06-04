<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #333;
        }

        .login-container {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }

        input[type="text"],
        input[type="password"] {
            padding: 10px;
            width: 100%;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        input[type="submit"] {
            background-color: #5c6bc0;
            color: white;
            border: none;
            padding: 12px;
            width: 100%;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #3f51b5;
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Admin Login</h2>
        <form method="post" action="AdminLogin.jsp">
            <input type="text" name="adminName" placeholder="Admin Name" required><br/>
            <input type="password" name="passWord" placeholder="Password" required><br/>
            <input type="submit" value="Login">
        </form>
        <%-- JSP login logic --%>
        <%
            if (request.getParameter("adminName") != null && request.getParameter("passWord") != null) {
                String user = request.getParameter("adminName");
                String pwd = request.getParameter("passWord");
        %>
            <jsp:useBean id="libAdmins" class="com.java.lib.model.LibAdmins" />
            <jsp:setProperty property="*" name="libAdmins"/>
            <jsp:useBean id="libraryDao" class="com.java.lib.dao.LibraryDaoImp" />
        
        <%
            int count = libraryDao.adminLogin(libAdmins);
            if (count >= 1) {
                session.setAttribute("admin", request.getParameter("adminName"));
        %>
            <jsp:forward page="AdminMenu.jsp" />
        <%
            } else {
        %>
            <div class="error-message">Invalid Credentials...</div>
        <%
            }
            }
        %>
    </div>
</body>
</html>
