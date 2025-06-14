<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Asset Management Navigation</title>

<!-- Tailwind CSS CDN -->
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-br from-blue-800 via-blue-900 to-black text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-4xl font-bold mb-10 drop-shadow-lg">Employ Asset Management</h1>

    <div class="flex flex-wrap gap-6">
        <a href="RequestAsset.jsf"
           class="px-6 py-3 bg-emerald-600 text-white font-semibold rounded-md shadow hover:bg-emerald-700 transition">
           Request Assets
        </a>

        <a href="ShowAssetHistory.jsf"
           class="px-6 py-3 bg-amber-600 text-white font-semibold rounded-md shadow hover:bg-amber-700 transition">
           See Asset History
        </a>

        <a href="ShowAssets.jsf"
           class="px-6 py-3 bg-fuchsia-600 text-white font-semibold rounded-md shadow hover:bg-fuchsia-700 transition">
           Show Assets
        </a>
	<a href="ShowAssetType.jsf"
           class="px-6 py-3 bg-fuchsia-600 text-white font-semibold rounded-md shadow hover:bg-fuchsia-700 transition">
           Show Asset by type
        </a>
        <a href="AdminLogin.jsf"
           class="px-6 py-3 bg-rose-600 text-white font-semibold rounded-md shadow hover:bg-rose-700 transition">
           Admin Dashboard
        </a>
    </div>

</body>
</html>
