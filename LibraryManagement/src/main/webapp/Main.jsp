<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Login</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #74ebd5, #ACB6E5);
            height: 100vh;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .container {
            background-color: white;
            padding: 40px 50px;
            border-radius: 15px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
            text-align: center;
        }

        h1 {
            margin-bottom: 10px;
            color: #333;
        }

        p {
            color: #666;
            font-size: 16px;
        }

        .login-buttons {
            margin-top: 30px;
        }

        .login-buttons form {
            display: inline-block;
            margin: 0 10px;
        }

        .login-buttons input {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 12px 24px;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .login-buttons input:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Library System</h1>
        <p>Please choose your login type:</p>
        <div class="login-buttons">
            <form action="AdminLogin.jsp" method="get">
                <input type="submit" value="Admin Login" />
            </form>
            <form action="LogIn.jsp" method="get">
                <input type="submit" value="User Login" />
            </form>
        </div>
    </div>
</body>
</html>
