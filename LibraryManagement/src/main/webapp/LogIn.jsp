<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Arial', sans-serif;
            background-color: #f7f7f7;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #333;
        }

        .login-container {
            background: #fff;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            font-size: 22px;
            color: #5d5d5d;
            font-weight: normal;
        }

        input[type="text"],
        input[type="password"] {
            padding: 12px;
            width: 100%;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 14px;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: #5c6bc0;
            outline: none;
        }

        input[type="submit"] {
            background-color: #5c6bc0;
            color: white;
            border: none;
            padding: 12px;
            width: 100%;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        input[type="submit"]:hover {
            background-color: #3f51b5;
            transform: translateY(-2px);
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }

        .forgot-password {
            font-size: 14px;
            color: #5c6bc0;
            text-decoration: none;
            margin-top: 15px;
            display: inline-block;
        }

        .forgot-password:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>User Login</h2>
        <form method="post" action="LogIn.jsp">
            <input type="text" name="userName" placeholder="Username" required><br/>
            <input type="password" name="passWord" placeholder="Password" required><br/>
            <input type="submit" value="Login">
        </form>
        
        <div class="divider"></div>
        
        <%-- JSP login logic --%>
        <%
            if (request.getParameter("userName") != null && request.getParameter("passWord") != null) {
                String user = request.getParameter("userName");
                String pwd = request.getParameter("passWord");
        %>
            <jsp:useBean id="libUsers" class="com.java.lib.model.LibUsers" />
            <jsp:setProperty property="*" name="libUsers"/>
            <jsp:useBean id="libraryDao" class="com.java.lib.dao.LibraryDaoImp" />
        
        <%
            int count = libraryDao.logIn(libUsers);
            if (count >= 1) {
                session.setAttribute("user", request.getParameter("userName"));
        %>
            <jsp:forward page="Menu.jsp" />
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
