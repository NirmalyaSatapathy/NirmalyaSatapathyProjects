<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Daily Expenses</title>
    <script src="https://cdn.tailwindcss.com"></script>

    <style>
        .data-table th, .data-table td {
            border: 1px solid #9CA3AF;
            padding: 0.5rem;
        }
        .data-table th {
            background-color: #16A34A;
            color: white;
            text-align: left;
        }
        .data-table {
            border-collapse: collapse;
            width: 100%;
        }
    </style>
</head>
<body class="bg-black min-h-screen font-sans text-gray-900 flex flex-col items-center justify-start p-6 space-y-6">

    <!-- Dashboard Top Navigation -->
    <div class="w-full max-w-4xl">
        <jsp:include page="DashBoard.jsp"/>
    </div>

    <!-- Main Content -->
    <div class="max-w-4xl w-full bg-white rounded-md shadow-md p-6">

        <!-- Header -->
        <header class="text-center mb-6">
            <h2 class="text-3xl font-bold text-green-700">Trip Daily Expenses</h2>
            <p class="text-gray-600">View and manage daily spending</p>
        </header>

        <!-- Set Group ID Form -->
        <h:form>
            <div class="flex space-x-3 mb-6">
                <h:inputText id="groupId" value="#{expenseController.groupId}" required="true"
                    styleClass="flex-grow p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"
                    onfocus="if(this.value=='Enter Group ID...')this.value='';"
                    onblur="if(this.value=='')this.value='Enter Group ID...';" />
                <h:commandButton value="Load" action="#{expenseController.dailyExpensesListMethod}"
                    styleClass="bg-green-300 text-black px-4 rounded cursor-pointer hover:bg-green-400" />
            </div>
        </h:form>

        <!-- Expenses Table -->
        <h:dataTable value="#{expenseController.dailyExpensesList}" var="expense" styleClass="data-table"
                     rendered="#{not empty expenseController.dailyExpensesList}">
            <h:column>
                <f:facet name="header"><h:outputText value="Expense ID" /></f:facet>
                <h:outputText value="#{expense.expId}" />
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="Description" /></f:facet>
                <h:outputText value="#{expense.expenseDescription}" />
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="Amount" /></f:facet>
                <h:outputText value="#{expense.amount}" />
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="Date" /></f:facet>
                <h:outputText value="#{expense.expenseDate}">
                    <f:convertDateTime pattern="yyyy-MM-dd" />
                </h:outputText>
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="Paid By (User ID)" /></f:facet>
                <h:outputText value="#{expense.paidBy}" />
            </h:column>
        </h:dataTable>

    </div>
</body>
</html>
</f:view>
