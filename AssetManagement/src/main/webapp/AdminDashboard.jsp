<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Asset Management</title>

<!-- Tailwind CSS CDN -->
<script src="https://cdn.tailwindcss.com"></script>

</head>
<body class="bg-gradient-to-br from-blue-800 via-blue-900 to-black text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-4xl font-bold mb-10 drop-shadow-lg">Admin Asset Management</h1>

    <div class="flex flex-wrap gap-6">
        <a href="AddAsset.jsf"
           class="px-6 py-3 bg-emerald-600 text-white font-semibold rounded-md shadow hover:bg-emerald-700 transition">
           Add Asset
        </a>

        <a href="ReturnAsset.jsf"
           class="px-6 py-3 bg-amber-600 text-white font-semibold rounded-md shadow hover:bg-amber-700 transition">
           Return Asset
        </a>

        <a href="ApproveAsset.jsf"
           class="px-6 py-3 bg-fuchsia-600 text-white font-semibold rounded-md shadow hover:bg-fuchsia-700 transition">
           Approve Asset
        </a>

        <a href="showPendingRequests.jsf"
           class="px-6 py-3 bg-rose-600 text-white font-semibold rounded-md shadow hover:bg-rose-700 transition">
           Show Pending Requests
        </a>
		<a href="ShowAssetType.jsf"
           class="px-6 py-3 bg-fuchsia-600 text-white font-semibold rounded-md shadow hover:bg-fuchsia-700 transition">
           Show Asset by type
        </a>
        <a href="EmployLogin.jsf"
           class="px-6 py-3 bg-teal-600 text-white font-semibold rounded-md shadow hover:bg-teal-700 transition">
           Employ Dashboard
        </a>
    </div>

</body>
</html>
