<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<!-- Tailwind CSS CDN -->
<script src="https://cdn.tailwindcss.com"></script>

</head>
<body class="bg-gradient-to-br from-blue-800 via-blue-900 to-black text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-4xl font-bold mb-10 drop-shadow-lg">Login Portal</h1>

    <div class="flex gap-6">
        <a href="AdminLogin.jsf"
           class="px-8 py-3 bg-emerald-600 text-white font-semibold rounded-md shadow hover:bg-emerald-700 transition">
           Admin Login
        </a>

        <a href="EmployLogin.jsf"
           class="px-8 py-3 bg-amber-600 text-white font-semibold rounded-md shadow hover:bg-amber-700 transition">
           Employ Login
        </a>
    </div>

</body>
</html>
