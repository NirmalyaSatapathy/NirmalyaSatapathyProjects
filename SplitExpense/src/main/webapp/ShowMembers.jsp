<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Group Members</title>
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

    <!-- Dashboard Include -->
    <div class="w-full max-w-4xl">
        <jsp:include page="DashBoard.jsp"/>
    </div>

    <!-- Main Content -->
    <div class="max-w-4xl w-full bg-white rounded-md shadow-md p-6">

        <!-- Header -->
        <header class="text-center mb-6">
            <h2 class="text-3xl font-bold text-green-700">Group Members</h2>
            <p class="text-gray-600">View all members in groups</p>
        </header>

        <!-- Members Table -->
        <h:dataTable value="#{userDao.showGroupMembers()}" var="member" styleClass="data-table"
                     rendered="#{not empty userDao.showGroupMembers()}">

            <h:column>
                <f:facet name="header"><h:outputText value="Member ID" /></f:facet>
                <h:outputText value="#{member.gmId}" />
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="Group ID" /></f:facet>
                <h:outputText value="#{member.groupId}" />
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="User ID" /></f:facet>
                <h:outputText value="#{member.userId}" />
            </h:column>

            <h:column>
                <f:facet name="header"><h:outputText value="Amount Collected" /></f:facet>
                <h:outputText value="#{member.amountCollected}" />
            </h:column>

        </h:dataTable>

    </div>

</body>
</html>
</f:view>
