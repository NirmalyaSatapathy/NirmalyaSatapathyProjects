<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trip Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-black min-h-screen flex items-center justify-center p-6 font-sans">
    <div class="bg-white max-w-7xl w-full p-8 rounded-md shadow-md text-center overflow-x-auto">

        <!-- Header -->
        <header class="mb-6">
            <h1 class="text-3xl font-bold text-green-700">Trip Dashboard</h1>
            <p class="text-gray-600">Navigate to different sections</p>
        </header>

        <!-- Navigation Buttons -->
        <div class="flex flex-wrap justify-center gap-4">

            <a href="showExpenses.jsf"
               class="px-6 py-2 bg-green-500 text-white rounded hover:bg-green-600 transition whitespace-nowrap">
                See Expenses
            </a>

            <a href="showSettlements.jsf"
               class="px-6 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition whitespace-nowrap">
                See Settlements
            </a>

            <a href="AddUser.jsf"
               class="px-6 py-2 bg-purple-500 text-white rounded hover:bg-purple-600 transition whitespace-nowrap">
                Add New User
            </a>

            <a href="AddTravelGroup.jsf"
               class="px-6 py-2 bg-yellow-500 text-white rounded hover:bg-yellow-600 transition whitespace-nowrap">
                Add New Group
            </a>

            <a href="AddDailyExpense.jsf"
               class="px-6 py-2 bg-red-500 text-white rounded hover:bg-red-600 transition whitespace-nowrap">
                Add Daily Expense
            </a>

            <a href="AddGroupMembers.jsf"
               class="px-6 py-2 bg-indigo-500 text-white rounded hover:bg-indigo-600 transition whitespace-nowrap">
                Add Group Members
            </a>

            <!-- ✅ New Navigation Buttons -->

            <a href="ShowGroups.jsf"
               class="px-6 py-2 bg-cyan-500 text-white rounded hover:bg-cyan-600 transition whitespace-nowrap">
                Show Groups
            </a>

            <a href="ShowUsers.jsf"
               class="px-6 py-2 bg-teal-500 text-white rounded hover:bg-teal-600 transition whitespace-nowrap">
                Show Users
            </a>

            <a href="ShowMembers.jsf"
               class="px-6 py-2 bg-pink-500 text-white rounded hover:bg-pink-600 transition whitespace-nowrap">
                Show Members
            </a>

        </div>
    </div>
</body>
</html>
