<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Add Daily Expense</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-black min-h-screen font-sans text-gray-900 flex flex-col items-center justify-start p-6 space-y-6">

    <!-- Dashboard Include -->
    <div class="w-full max-w-4xl">
        <jsp:include page="DashBoard.jsp" />
    </div>

    <!-- Form Container -->
    <div class="max-w-4xl w-full bg-white rounded-md shadow-md p-6">
        <!-- Header -->
        <header class="text-center mb-6">
            <h2 class="text-3xl font-bold text-green-700">Add Daily Expense</h2>
            <p class="text-gray-600">Enter the expense details below</p>
        </header>

        <h:form prependId="false" styleClass="space-y-4">
            <!-- Group ID -->
            <div>
                <h:outputLabel for="groupId" value="Group ID" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="groupId" value="#{dailyExpenses.groupId}"
                    styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400" />
            </div>

            <!-- Expense Date -->
            <div>
                <h:outputLabel for="expenseDate" value="Expense Date" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="expenseDate" value="#{dailyExpenses.expenseDate}"
                    styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400" >
                    <f:convertDateTime pattern="yyyy-MM-dd" />
                </h:inputText>
            </div>

            <!-- Expense Description -->
            <div>
                <h:outputLabel for="expenseDescription" value="Expense Description" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="expenseDescription" value="#{dailyExpenses.expenseDescription}"
                    styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400" />
            </div>

            <!-- Paid By -->
            <div>
                <h:outputLabel for="paidBy" value="Paid By (User ID)" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="paidBy" value="#{dailyExpenses.paidBy}"
                    styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400" />
            </div>

            <!-- Amount -->
            <div>
                <h:outputLabel for="amount" value="Amount" styleClass="block mb-1 text-gray-700 font-semibold" />
                <h:inputText id="amount" value="#{dailyExpenses.amount}"
                    styleClass="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400" />
            </div>

            <!-- Submit Button -->
            <div class="text-center mt-4">
                <h:commandButton value="Add Expense" action="#{userDao.addDailyExpenses(dailyExpenses)}"
                    styleClass="bg-green-500 text-white px-6 py-2 rounded hover:bg-green-600 transition cursor-pointer" />
            </div>
        </h:form>
    </div>
</body>
</html>
</f:view>
