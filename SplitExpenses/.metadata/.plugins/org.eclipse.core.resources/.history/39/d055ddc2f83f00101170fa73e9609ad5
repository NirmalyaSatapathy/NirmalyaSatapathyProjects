<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Daily Expenses</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen">
    <div class="container mx-auto p-6">
        <h2 class="text-3xl font-bold mb-4">Daily Expenses</h2>

        <!-- Set Group ID Form -->
        <h:form>
            <div class="mb-4">
                <h:outputLabel for="groupId" value="Enter Group ID:" />
                <h:inputText id="groupId" value="#{expenseController.groupId}"
                    required="true" styleClass="w-full border p-2 rounded" />
            </div>
            <h:commandButton value="Load Expenses"
                action="#{expenseController.dailyExpensesListMethod}"
                styleClass="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700" />
        </h:form>

        <!-- Expenses Table -->
        <h:form>
            <h:dataTable
                value="#{expenseController.dailyExpensesList}"
                var="expense"
                styleClass="mt-6 w-full table-auto border-collapse border border-gray-300"
                headerClass="bg-blue-600 text-white text-left px-4 py-2"
                rowClasses="bg-white text-gray-700 border-b">

                <h:column>
                    <f:facet name="header">Expense ID</f:facet>
                    <h:outputText value="#{expense.expId}" />
                </h:column>
                <h:column>
                    <f:facet name="header">Description</f:facet>
                    <h:outputText value="#{expense.expenseDescription}" />
                </h:column>
                <h:column>
                    <f:facet name="header">Amount</f:facet>
                    <h:outputText value="#{expense.amount}" />
                </h:column>
                <h:column>
                    <f:facet name="header">Date</f:facet>
                    <h:outputText value="#{expense.expenseDate}">
                        <f:convertDateTime pattern="yyyy-MM-dd" />
                    </h:outputText>
                </h:column>
                <h:column>
                    <f:facet name="header">Paid By (User ID)</f:facet>
                    <h:outputText value="#{expense.paidBy}" />
                </h:column>
            </h:dataTable>
        </h:form>
    </div>
</body>
</html>
</f:view>
